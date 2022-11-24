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
import epic.legofullstack.fourpaws.core.domain.model.PetFilter
import epic.legofullstack.fourpaws.core.domain.usecase.PetFilterDataStoreUseCase
import epic.legofullstack.fourpaws.core.domain.usecase.PreferenceDataStoreUseCase
import epic.legofullstack.fourpaws.core.presentation.ResourcesProvider
import epic.legofullstack.fourpaws.feature.base.BaseViewModel
import epic.legofullstack.fourpaws.feature.base.OpenFragment
import epic.legofullstack.fourpaws.feature.base.ShowSnackbar
import epic.legofullstack.fourpaws.feature.filter.data.models.toFilterModel
import epic.legofullstack.fourpaws.feature.filter.data.models.toSaveFilter
import epic.legofullstack.fourpaws.feature.filter.domain.model.PetFilterModel
import epic.legofullstack.fourpaws.feature.filter.domain.usecase.GetAreasUseCase
import epic.legofullstack.fourpaws.feature.filter.presentation.state.UiState
import epic.legofullstack.fourpaws.network.errorhandle.ResponseState
import epic.legofullstack.fourpaws.network.firebase.data.model.Age
import epic.legofullstack.fourpaws.network.firebase.data.model.PetType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val getAreasUseCase: GetAreasUseCase,
    private val areaDataStoreUseCase: PreferenceDataStoreUseCase,
    private val filterDataStoreUseCase: PetFilterDataStoreUseCase,
    @DispatchersModule.IoDispatcher private val io: CoroutineDispatcher,
    @DispatchersModule.MainDispatcher private val main: CoroutineDispatcher,
    private val resourcesProvider: ResourcesProvider
) : BaseViewModel() {
    private var tempFilter = PetFilterModel()

    private val content = MutableLiveData<UiState>(UiState.Loading)
    private var currentContent = UiState.Content()
    val filterState: LiveData<UiState> get() = content

    init {
        viewModelScope.launch {
            content.value = UiState.Loading
            withContext(io) {
                val areas = async { getAreasUseCase() }.await()
                val userArea = async { areaDataStoreUseCase.getUserArea() }.await()
                val filterPet = async { filterDataStoreUseCase.getPetFilter() }.await()

                if (areas is ResponseState.Success && userArea is ResponseState.Success && filterPet is ResponseState.Success) {
                    handleSuccess(areas.data, userArea.data, filterPet.data)
                } else {
                    handleError(resourcesProvider.getString(R.string.error_reading_data))
                }
            }
        }
    }

    private suspend fun handleError(error: String) {
        withContext(main) {
            commands.value = ShowSnackbar(
                text = error
            )
            content.value = currentContent
        }
    }

    private suspend fun handleSuccess(
        areas: List<Area>,
        currentUserArea: Area,
        currentFilterPet: PetFilter
    ) {
        withContext(main) {
            if (areas.isEmpty()) {
                commands.value = ShowSnackbar(
                    text = resourcesProvider.getString(R.string.error_loading_areas)
                )
            }
            var filter = currentFilterPet
            // filter isEmpty
            if (currentFilterPet.area.id == AREA_ID_DEFAULT) {
                filter = currentFilterPet.copy(area = currentUserArea)
            }
            tempFilter = filter.toFilterModel()
            currentContent = currentContent.copy(
                areas = areas,
                userArea = currentUserArea.title,
                filter = tempFilter
            )
            content.value = currentContent
        }
    }

    fun setArea(area: Area) {
        viewModelScope.launch {
            withContext(main) {
                tempFilter = tempFilter.copy(area = area)
            }
        }
    }

    fun updateFilter(typeField: String, chipGroup: ChipGroup) {
        viewModelScope.launch {
            withContext(main) {
                val checkedChip =
                    chipGroup.children.firstOrNull { it.id == chipGroup.checkedChipId } as Chip?
                val checkedChipText = checkedChip?.text?.toString()
                tempFilter = when(typeField) {
                    AGE_FIELD -> tempFilter.copy(age = checkedChipText?.let { parseToAge(it) })
                    GENDER_FIELD -> tempFilter.copy(gender = checkedChipText?.let { parseToGender(it) })
                    PET_TYPE_FIELD -> tempFilter.copy(petType = checkedChipText?.let { parseToPetType(it) })
                    else -> tempFilter
                }
            }
        }
    }

    fun filterByCharacteristics(chipGroup: ChipGroup, ids: MutableList<Int>) {
        viewModelScope.launch {
            withContext(main) {
                val characteristic = checkedChipsTexts(chipGroup, ids)
                tempFilter = tempFilter.copy(characteristics = characteristic.ifEmpty { null })
            }
        }
    }

    private fun checkedChipsTexts(chipGroup: ChipGroup, ids: MutableList<Int>) =
        chipGroup.children
            .filter { ids.contains(it.id) }
            .map { (it as Chip).text.toString().parseToCharacteristic(chipGroup.context) }
            .toList()

    private fun String.parseToCharacteristic(context: Context) = when (this) {
        context.getString(R.string.ru_vaccinated) -> context.getString(R.string.vaccinated)
        context.getString(R.string.ru_accustomed_tray) -> context.getString(R.string.accustomed_tray)
        context.getString(R.string.ru_sterilised) -> context.getString(R.string.sterilized)
        context.getString(R.string.ru_friendly) -> context.getString(R.string.friendly)
        else -> ""
    }

    fun applyFilter() {
        viewModelScope.launch {
            content.value = UiState.Loading
            withContext(io) {
                currentContent =
                    currentContent.copy(filter = tempFilter, userArea = tempFilter.area.title)
                val saveArea = async { areaDataStoreUseCase.saveUserArea(tempFilter.area) }.await()
                val saveFilter =
                    async { filterDataStoreUseCase.savePetFilter(tempFilter.toSaveFilter()) }.await()
                if (saveArea is ResponseState.Success && saveFilter is ResponseState.Success)
                    handleSuccessSave()
                else
                    handleError(resourcesProvider.getString(R.string.error_data_filling))
            }
        }
    }

    private fun handleSuccessSave() {
        viewModelScope.launch {
            withContext(main) {
                commands.value =
                    OpenFragment(actionId = R.id.action_filterMenuItem_to_navigation_home)
            }
        }
    }

    private fun parseToPetType(type: String): PetType? =
        when (type) {
            resourcesProvider.getString(R.string.cats) -> PetType.CAT
            resourcesProvider.getString(R.string.dogs) -> PetType.DOG
            else -> null
        }

    private fun parseToGender(gender: String): String? =
        when (gender) {
            resourcesProvider.getString(R.string.boys) -> resourcesProvider.getString(R.string.male)
            resourcesProvider.getString(R.string.girls) -> resourcesProvider.getString(R.string.female)
            else -> null
        }

    private fun parseToAge(age: String): Age? = when (age) {
        resourcesProvider.getString(R.string.baby) -> Age.BABY
        resourcesProvider.getString(R.string.young) -> Age.YOUNG
        resourcesProvider.getString(R.string.adult) -> Age.ADULT
        resourcesProvider.getString(R.string.elderly) -> Age.ELDERLY
        else -> null
    }

    companion object {
        private const val AREA_ID_DEFAULT = 0
        const val AGE_FIELD = "age"
        const val GENDER_FIELD = "gender"
        const val PET_TYPE_FIELD = "petType"

    }
}