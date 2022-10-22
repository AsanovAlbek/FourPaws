package epic.legofullstack.fourpaws.feature.favorites.presentation.model

import epic.legofullstack.fourpaws.core.presentation.model.ErrorModel
import epic.legofullstack.fourpaws.feature.favorites.domain.model.FavoritePet

sealed class UiState {
    object Loading: UiState()
    data class Error(val errorModel: ErrorModel)
    data class Content(val favorites: List<FavoritePet>)
}