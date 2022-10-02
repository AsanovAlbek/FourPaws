package epic.legofullstack.fourpaws.feature.favorites.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import epic.legofullstack.fourpaws.dispatchers.DispatchersModule
import epic.legofullstack.fourpaws.feature.favorites.domain.model.FavoritePet
import epic.legofullstack.fourpaws.feature.favorites.domain.usecase.GetFavoritePetsUseCase
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritePetsUseCase: GetFavoritePetsUseCase,
    @DispatchersModule.MainDispatcher private val mainDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _favoritePets = MutableLiveData<List<FavoritePet>>()
    val favoritePets get() = _favoritePets

    fun getFavoritePets() {
        viewModelScope.launch {
            val pets = getFavoritePetsUseCase()
            withContext(mainDispatcher) {
                _favoritePets.postValue(pets)
            }
        }
    }

}