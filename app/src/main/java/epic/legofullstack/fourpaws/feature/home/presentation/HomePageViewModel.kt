package epic.legofullstack.fourpaws.feature.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.core.di.DispatchersModule
import epic.legofullstack.fourpaws.core.domain.usecase.PetFilterDataStoreUseCase
import epic.legofullstack.fourpaws.core.domain.usecase.PreferenceDataStoreUseCase
import epic.legofullstack.fourpaws.core.presentation.ResourcesProvider
import epic.legofullstack.fourpaws.feature.base.BaseViewModel
import epic.legofullstack.fourpaws.feature.base.OpenFragment
import epic.legofullstack.fourpaws.feature.base.ShowSnackbar
import epic.legofullstack.fourpaws.feature.home.domain.model.Pet
import epic.legofullstack.fourpaws.feature.home.domain.usecase.GetAllPetsUseCase
import epic.legofullstack.fourpaws.feature.home.domain.usecase.PetFilterUseCase
import epic.legofullstack.fourpaws.feature.home.presentation.dto.UiState
import epic.legofullstack.fourpaws.network.errorhandle.ResponseState
import epic.legofullstack.fourpaws.network.errorhandle.handleResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val getAllPetsUseCase: GetAllPetsUseCase,
    private val petFilterUseCase: PetFilterUseCase,
    @DispatchersModule.MainDispatcher
    private val mainDispatcher: CoroutineDispatcher,
    @DispatchersModule.IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val areaStorage: PreferenceDataStoreUseCase,
    private val filterDataStoreUseCase: PetFilterDataStoreUseCase,
    private val provider: ResourcesProvider
) : BaseViewModel() {

    private val content = MutableLiveData<UiState>()
    val state: LiveData<UiState> get() = content

    fun executeWhenCreated() {
        viewModelScope.launch {
            content.value = UiState.Loading
            withContext(ioDispatcher) {
                val userArea = async { areaStorage.getUserArea() }.await()
                val filterPet = async { filterDataStoreUseCase.getPetFilter() }.await()

                if (userArea is ResponseState.Success && filterPet is ResponseState.Success) {
                    if (filterPet.data.area.id != 0) {
                        petFilterUseCase(filterPet.data).handleResult(
                            { error -> handleError(error) },
                            { handleSuccess(it, true) }
                        )
                    } else {
                        getAllPetsUseCase(userArea.data.id).handleResult(
                            { error -> handleError(error) },
                            { handleSuccess(it) })
                    }
                } else {
                    handleError(false)
                }
            }
        }
    }

    private suspend fun handleError(networkError: Boolean) {
        withContext(mainDispatcher) {
            content.value = UiState.Error(errorModel = networkError.parseError())
        }
    }

    private suspend fun handleSuccess(data: List<Pet>, filterUsed: Boolean = false) {
        withContext(mainDispatcher) {
            content.value = UiState.Content(pets = data)
            if (data.isEmpty()) {
                val text =
                    if (filterUsed) provider.getString(R.string.pets_not_filter)
                    else provider.getString(R.string.pets_not_found)
                commands.value = ShowSnackbar(text = text)
            }
        }
    }

    fun clickToPet(petId: Int) {
        viewModelScope.launch {
            withContext(ioDispatcher) {
                openPetDetails(petId)
            }
        }
    }

    private suspend fun openPetDetails(petId: Int) {
        withContext(mainDispatcher) {
            val action = FragmentHomePageDirections.actionNavigationHomeToNavigationDetail(petId)
            commands.value = OpenFragment(directions = action)
        }
    }
}