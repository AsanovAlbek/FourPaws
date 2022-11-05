package epic.legofullstack.fourpaws.feature.home.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.lifecycle.HiltViewModel
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.core.di.DispatchersModule
import epic.legofullstack.fourpaws.core.presentation.ResourcesProvider
import epic.legofullstack.fourpaws.extensions.navigateSafely
import epic.legofullstack.fourpaws.feature.base.BaseViewModel
import epic.legofullstack.fourpaws.feature.base.OpenFragment
import epic.legofullstack.fourpaws.feature.base.ShowSnackbar
import epic.legofullstack.fourpaws.network.errorhandle.ResponseState
import epic.legofullstack.fourpaws.feature.home.domain.model.Pet
import epic.legofullstack.fourpaws.feature.home.domain.usecase.GetAllPetsUseCase
import epic.legofullstack.fourpaws.feature.home.domain.usecase.GetPetByIdUseCase
import epic.legofullstack.fourpaws.feature.home.presentation.dto.UiState
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val getAllPetsUseCase : GetAllPetsUseCase,
    private val getPetByIdUseCase: GetPetByIdUseCase,
    @DispatchersModule.MainDispatcher private val mainDispatcher: CoroutineDispatcher,
    @DispatchersModule.IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val provider: ResourcesProvider
    ) : BaseViewModel() {

    private val content = MutableLiveData<UiState>()
    val state : LiveData<UiState> get() = content

    fun executeWhenCreated() {
        viewModelScope.launch {
            content.value = UiState.Loading
            withContext(ioDispatcher) {
                when(val response = getAllPetsUseCase()) {
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
        }
    }

    fun clickToPet(petId: Int) {
        viewModelScope.launch {
            withContext(ioDispatcher) {
                when(val response = getPetByIdUseCase(petId)) {
                    is ResponseState.Success -> openPetDetails(petId)
                    is ResponseState.Error -> connectedLostSnackBar(response.isNetworkError)
                }
            }
        }
    }

    private suspend fun connectedLostSnackBar(networkError: Boolean) { 
        commands.value = ShowSnackbar(
            text = provider.getString(R.string.no_internet_message)
        )
    }

    private suspend fun openPetDetails(petId: Int) {
        withContext(mainDispatcher) {
            val action = FragmentHomePageDirections.actionNavigationHomeToNavigationDetail(petId)
            commands.value = OpenFragment(directions = action)
        }
    }

}