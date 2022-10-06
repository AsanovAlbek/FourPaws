package epic.legofullstack.fourpaws.feature.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import epic.legofullstack.fourpaws.core.di.DispatchersModule
import epic.legofullstack.fourpaws.network.errorhandle.ResponseState
import epic.legofullstack.fourpaws.feature.home.domain.model.Pet
import epic.legofullstack.fourpaws.feature.home.domain.usecase.GetAllPetsUseCase
import epic.legofullstack.fourpaws.feature.home.presentation.dto.UiState
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val getAllPetsUseCase : GetAllPetsUseCase,
    @DispatchersModule.MainDispatcher private val mainDispatcher: CoroutineDispatcher,
    @DispatchersModule.IoDispatcher private val ioDispatcher: CoroutineDispatcher
    ) : ViewModel() {

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


}