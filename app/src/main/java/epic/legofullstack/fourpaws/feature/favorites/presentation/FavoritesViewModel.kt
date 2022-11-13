package epic.legofullstack.fourpaws.feature.favorites.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import epic.legofullstack.fourpaws.core.di.DispatchersModule
import epic.legofullstack.fourpaws.core.presentation.ResourcesProvider
import epic.legofullstack.fourpaws.feature.base.BaseViewModel
import epic.legofullstack.fourpaws.feature.base.OpenFragment
import epic.legofullstack.fourpaws.feature.favorites.domain.model.FavoritePet
import epic.legofullstack.fourpaws.feature.favorites.domain.usecase.GetFavoritePetsUseCase
import epic.legofullstack.fourpaws.feature.favorites.presentation.state.FavoriteState
import epic.legofullstack.fourpaws.feature.home.presentation.parseError
import epic.legofullstack.fourpaws.network.errorhandle.ResponseState
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritePetsUseCase: GetFavoritePetsUseCase,
    @DispatchersModule.MainDispatcher private val mainDispatcher: CoroutineDispatcher,
    @DispatchersModule.IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val resourcesProvider: ResourcesProvider
): BaseViewModel() {

    private val content = MutableLiveData<FavoriteState>()
    val state get() = content

    fun getFavoritePets() {
        viewModelScope.launch {
            content.value = FavoriteState.Loading
            withContext(ioDispatcher) {
                when(val response = getFavoritePetsUseCase()) {
                    is ResponseState.Success -> handleContent(response.data)
                    is ResponseState.Error -> handleError(response.isNetworkError)
                }
            }
        }
    }

    private suspend fun handleError(isNetworkError: Boolean) {
        withContext(mainDispatcher) {
            content.value = FavoriteState.Error(error = isNetworkError.parseError())
        }
    }

    private suspend fun handleContent(list: List<FavoritePet>) {
        withContext(mainDispatcher) {
            content.value = FavoriteState.Content(pets = list)
        }
    }

    fun clickToFavorite(favoriteId: Int) {
        viewModelScope.launch {
            openFavorite(favoriteId)
        }
    }

    private suspend fun openFavorite(favoriteId: Int) {
        withContext(mainDispatcher) {
            val action = FragmentFavoritesDirections.actionNavigationFavoriteToNavigationDetail(
                clickedPetId = favoriteId
            )
            commands.value = OpenFragment(directions = action)
        }
    }

}