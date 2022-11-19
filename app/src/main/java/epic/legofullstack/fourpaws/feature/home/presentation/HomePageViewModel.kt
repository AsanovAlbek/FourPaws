package epic.legofullstack.fourpaws.feature.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.core.di.DispatchersModule
import epic.legofullstack.fourpaws.core.domain.usecase.PreferenceDataStoreUseCase
import epic.legofullstack.fourpaws.core.presentation.ResourcesProvider
import epic.legofullstack.fourpaws.feature.base.BaseViewModel
import epic.legofullstack.fourpaws.feature.base.OpenFragment
import epic.legofullstack.fourpaws.feature.base.ShowSnackbar
import epic.legofullstack.fourpaws.feature.home.domain.model.Pet
import epic.legofullstack.fourpaws.feature.home.domain.usecase.GetAllPetsUseCase
import epic.legofullstack.fourpaws.feature.home.presentation.dto.UiState
import epic.legofullstack.fourpaws.network.errorhandle.ResponseState
import epic.legofullstack.fourpaws.network.errorhandle.handleResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val getAllPetsUseCase : GetAllPetsUseCase,
    @DispatchersModule.MainDispatcher
    private val mainDispatcher: CoroutineDispatcher,
    @DispatchersModule.IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val areaStorage: PreferenceDataStoreUseCase,
    private val provider: ResourcesProvider
    ) : BaseViewModel() {

    private val content = MutableLiveData<UiState>()
    val state : LiveData<UiState> get() = content

    fun executeWhenCreated() {
        viewModelScope.launch {
            content.value = UiState.Loading
            areaStorage.getUserArea().handleResult {
                when(val response = getAllPetsUseCase(it.id)) {
                    is ResponseState.Success -> handleSuccess(response.data)
                    is ResponseState.Error -> handleError(response.isNetworkError)
                }
            }
        }
    }

    private suspend fun handleError(networkError: Boolean) {
        withContext(mainDispatcher) {
            content.value = UiState.Error(errorModel = networkError.parseError())
        }
    }

    private suspend fun handleSuccess(data: List<Pet>) {
        withContext(mainDispatcher) {
            content.value = UiState.Content(pets = data)
            if(data.isEmpty()) {
                commands.value = ShowSnackbar(text = provider.getString(R.string.pets_not_found))
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