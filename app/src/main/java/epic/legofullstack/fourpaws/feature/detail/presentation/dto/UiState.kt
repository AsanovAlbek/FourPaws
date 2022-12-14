package epic.legofullstack.fourpaws.feature.detail.presentation.dto

import epic.legofullstack.fourpaws.core.presentation.model.ErrorModel
import epic.legofullstack.fourpaws.feature.detail.domain.model.PetDetail

sealed class UiState {
    object Loading: UiState()
    data class Content(val pet: PetDetail = PetDetail()): UiState()
    data class Error(val errorModel: ErrorModel): UiState()
}