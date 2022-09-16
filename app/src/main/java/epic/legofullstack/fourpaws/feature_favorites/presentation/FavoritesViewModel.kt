package epic.legofullstack.fourpaws.feature_favorites.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import epic.legofullstack.fourpaws.feature_favorites.domain.model.FavoritePet
import epic.legofullstack.fourpaws.feature_favorites.domain.usecase.GetFavoritePetsUseCase
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritePetsUseCase: GetFavoritePetsUseCase
): ViewModel() {

    private val _favoritePets = MutableLiveData<List<FavoritePet>>()
    val favoritePets get() = _favoritePets

    fun getFavoritePets() {
        viewModelScope.launch {
            val pets = getFavoritePetsUseCase()
            _favoritePets.postValue(pets)
        }
    }

}