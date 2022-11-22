package epic.legofullstack.fourpaws.feature.filter.presentation
import android.content.Context
import androidx.core.view.children
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.lifecycle.HiltViewModel
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.core.di.DispatchersModule
import epic.legofullstack.fourpaws.core.domain.model.Area
import epic.legofullstack.fourpaws.core.domain.usecase.PreferenceDataStoreUseCase
import epic.legofullstack.fourpaws.core.presentation.ResourcesProvider
import epic.legofullstack.fourpaws.feature.base.BaseViewModel
import epic.legofullstack.fourpaws.feature.base.OpenFragment
import epic.legofullstack.fourpaws.feature.base.ShowSnackbar
import epic.legofullstack.fourpaws.feature.filter.domain.model.PetFilterModel
import epic.legofullstack.fourpaws.feature.filter.domain.usecase.GetAreasUseCase
import epic.legofullstack.fourpaws.feature.filter.presentation.state.UiState
import epic.legofullstack.fourpaws.network.errorhandle.ResponseState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class FilterViewModel(
    private val getAreasUseCase: GetAreasUseCase,
    private val saveAreaUseCase: PreferenceDataStoreUseCase,
    @DispatchersModule.IoDispatcher private val io: CoroutineDispatcher,
    @DispatchersModule.MainDispatcher private val main: CoroutineDispatcher,
    private val resourcesProvider: ResourcesProvider
): BaseViewModel() {

    private val _filter = MutableLiveData<PetFilterModel>()
    val filter: LiveData<PetFilterModel> get() = _filter
    private var tempFilter = PetFilterModel()

    private val content = MutableLiveData<UiState>()
    val areaListState: LiveData<UiState> get() = content

    fun getAreas() {
        viewModelScope.launch {
            content.value = UiState.Loading
            withContext(io) {
                when(val areas = getAreasUseCase()) {
                    is ResponseState.Success -> handleSuccess(areas.data)
                    is ResponseState.Error -> handleError()
                }
            }
        }
    }

    private suspend fun handleError() {
        withContext(main) {
            commands.value = ShowSnackbar(
                text = resourcesProvider.getString(R.string.error_loading_areas)
            )
        }
    }

    private suspend fun handleSuccess(data: List<Area>) {
        withContext(main) {
            if (data.isEmpty()) {
                commands.value = ShowSnackbar(
                    text = resourcesProvider.getString(R.string.error_loading_areas)
                )
            }
            content.value = UiState.Content(data)
        }
    }

    fun filterByAreaId(areaId: Int = 0) {
        viewModelScope.launch {
            withContext(main) {
                if (areaId != 0) {
                    tempFilter = tempFilter.copy(areaId = areaId)
                    _filter.value = tempFilter
                }
            }
        }
    }

    fun saveArea(area: Area) {
        viewModelScope.launch {
            withContext(io) {
                saveAreaUseCase.saveUserArea(area)
            }
        }
    }

    fun filterByPetType(petType: String) {
        viewModelScope.launch {
            withContext(main) {
                if (petType.isNotEmpty()) {
                    tempFilter = tempFilter.copy(petType = petType)
                    _filter.value = tempFilter
                }
            }
        }
    }

    fun filterByGender(gender: String) {
        viewModelScope.launch {
            withContext(main) {
                if (gender.isNotEmpty()) {
                    tempFilter = tempFilter.copy(gender = gender)
                    _filter.value = tempFilter
                }
            }
        }
    }

    fun filterByAge(chipGroup: ChipGroup) {
        viewModelScope.launch {
            withContext(main) {
                val checkedChip =
                    chipGroup.children.firstOrNull { it.id == chipGroup.checkedChipId } as Chip?
                val checkedChipText = checkedChip?.text?.toString() ?: ""
                if (checkedChipText.isNotEmpty()) {
                    tempFilter = tempFilter.copy(age = checkedChipText)
                    _filter.value = tempFilter
                }
            }
        }
    }

    fun filterByCharacteristics(chipGroup: ChipGroup, ids: MutableList<Int>) {
        viewModelScope.launch {
            withContext(main) {
                val characteristic = checkedChipsTexts(chipGroup, ids)
                if (characteristic.isNotEmpty()) {
                    tempFilter = tempFilter.copy(characteristics = characteristic)
                    _filter.value = tempFilter
                }
            }
        }
    }

    private fun checkedChipsTexts(chipGroup: ChipGroup, ids: MutableList<Int>) =
        chipGroup.children
            .filter { ids.contains(it.id) }
            .map { (it as Chip).text.toString().parseToCharacteristic(chipGroup.context) }
            .toList()

    private fun String.parseToCharacteristic(context: Context) = when(this) {
        context.getString(R.string.ru_vaccinated) -> context.getString(R.string.vaccinated)
        context.getString(R.string.ru_accustomed_tray) -> context.getString(R.string.accustomed_tray)
        context.getString(R.string.ru_sterilised) -> context.getString(R.string.sterilized)
        context.getString(R.string.ru_friendly) -> context.getString(R.string.friendly)
        else -> ""
    }

    private fun String.parseToAge(context: Context) = when(this) {
        context.getString(R.string.baby) -> context.getString(R.string.baby_eng)
        context.getString(R.string.young) -> context.getString(R.string.young_eng)
        context.getString(R.string.adult) -> context.getString(R.string.adult_eng)
        context.getString(R.string.elderly) -> context.getString(R.string.elderly_eng)
        else -> ""
    }


    fun applyFilter() {
        viewModelScope.launch {
            withContext(main) {
                val filterAction =
                    _filter.value?.let {
                        FilterPetFragmentDirections.actionFilterMenuItemToNavigationHome(it)
                    }
                commands.value = OpenFragment(directions = filterAction)
            }
        }
    }
}