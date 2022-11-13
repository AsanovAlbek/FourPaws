package epic.legofullstack.fourpaws.feature.detail.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.core.di.DispatchersModule
import epic.legofullstack.fourpaws.core.presentation.ResourcesProvider
import epic.legofullstack.fourpaws.feature.base.BaseViewModel
import epic.legofullstack.fourpaws.feature.base.NavigateUp
import epic.legofullstack.fourpaws.feature.base.ShowDialog
import epic.legofullstack.fourpaws.feature.detail.domain.model.Pet
import epic.legofullstack.fourpaws.feature.detail.domain.usecase.AddOrRemoveInFavoritesUseCase
import epic.legofullstack.fourpaws.feature.detail.domain.usecase.FindPetByIdUseCase
import epic.legofullstack.fourpaws.feature.detail.presentation.dto.UiState
import epic.legofullstack.fourpaws.feature.home.presentation.parseError
import epic.legofullstack.fourpaws.network.errorhandle.ResponseState
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val findPetByIdUseCase: FindPetByIdUseCase,
    private val addOrRemoveInFavoritesUseCase: AddOrRemoveInFavoritesUseCase,
    private val resourcesProvider: ResourcesProvider,

    @DispatchersModule.IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    @DispatchersModule.MainDispatcher
    private val mainDispatcher: CoroutineDispatcher
): BaseViewModel() {
    private val content = MutableLiveData<UiState>()
    val state: LiveData<UiState> get() = content

    private var bufferContent = UiState.Content()

    fun findPet(id: Int, navController: NavController) {
        viewModelScope.launch {
            content.value = UiState.Loading
            withContext(ioDispatcher) {
                when(val pet = findPetByIdUseCase(id)) {
                    is ResponseState.Success -> showPetInfo(pet.data)
                    is ResponseState.Error -> showError(pet.isNetworkError, navController)
                }
            }
        }
    }

    fun addPetToFavorite(pet: Pet) {
        viewModelScope.launch {
            withContext(ioDispatcher) {
                addOrRemoveInFavoritesUseCase.addPetToFavorites(pet = pet)
                withContext(mainDispatcher) {
                    bufferContent = bufferContent.copy(pet = pet.addToFavorites())
                    content.value = bufferContent
                }
            }
        }
    }

    fun removePetToFavorite(pet: Pet) {
        viewModelScope.launch {
            withContext(ioDispatcher) {
                addOrRemoveInFavoritesUseCase.removePetToFavorites(pet = pet)
                withContext(mainDispatcher) {
                    bufferContent = bufferContent.copy(pet = pet.removeToFavorites())
                    content.value = bufferContent
                }
            }
        }
    }

    private suspend fun showError(networkError: Boolean, navController: NavController) {
        withContext(mainDispatcher) {
            content.value = UiState.Error(networkError.parseError())
            commands.value = ShowDialog(
                title = resourcesProvider.getString(R.string.error),
                message = resourcesProvider.getString(R.string.no_internet_message),
                positiveButtonText = resourcesProvider.getString(R.string.ok),
                callbackPositiveButton = { commands.value = NavigateUp(navController) }
            )
        }
    }

    private suspend fun showPetInfo(data: Pet) {
        withContext(mainDispatcher) {
            bufferContent = bufferContent.copy(pet = data)
            content.value = UiState.Content(data)
        }
    }
}