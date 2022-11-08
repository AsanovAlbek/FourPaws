package epic.legofullstack.fourpaws.feature.favorites.presentation.state

import epic.legofullstack.fourpaws.core.presentation.model.ErrorModel
import epic.legofullstack.fourpaws.feature.favorites.domain.model.FavoritePet

sealed class FavoriteState {
    object Loading: FavoriteState()
    class Content(val pets: List<FavoritePet>): FavoriteState()
    class Error(val error: ErrorModel): FavoriteState()
}
