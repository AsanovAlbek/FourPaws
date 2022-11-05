package epic.legofullstack.fourpaws.feature.favorites.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import epic.legofullstack.fourpaws.core.di.DispatchersModule
import epic.legofullstack.fourpaws.feature.base.BaseViewModel
import epic.legofullstack.fourpaws.feature.favorites.domain.model.FavoritePet
import epic.legofullstack.fourpaws.feature.favorites.domain.usecase.GetFavoritePetsUseCase
import epic.legofullstack.fourpaws.feature.favorites.presentation.model.UiState
import epic.legofullstack.fourpaws.network.errorhandle.ResponseState
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritePetsUseCase: GetFavoritePetsUseCase,
    @DispatchersModule.IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DispatchersModule.MainDispatcher private val mainDispatcher: CoroutineDispatcher
): BaseViewModel() {

    private val content = MutableLiveData<UiState>()
    val state: LiveData<UiState> get() = content

    fun getFavoritePets() {
        viewModelScope.launch {
            content.value = UiState.Loading
            withContext(ioDispatcher) {
                when(val response = getFavoritePetsUseCase()) {
                    is ResponseState.Success -> handleSuccess(response.data)
                    is ResponseState.Error -> handleError(response.isNetworkError)
                }
            }
        }
    }

    private fun handleError(networkError: Boolean) {
        // todo обработка ошибки
    }

    private fun handleSuccess(data: List<FavoritePet>) {
        // todo получение результата
    }

}