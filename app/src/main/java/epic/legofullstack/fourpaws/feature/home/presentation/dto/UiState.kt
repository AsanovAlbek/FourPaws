package epic.legofullstack.fourpaws.feature.home.presentation.dto

import epic.legofullstack.fourpaws.core.presentation.model.ErrorModel
import epic.legofullstack.fourpaws.feature.home.domain.model.Pet

sealed class UiState {
    object Loading : UiState()
    data class Content(val pets : List<Pet>) : UiState()
    data class Error(val errorModel : ErrorModel) : UiState()
}