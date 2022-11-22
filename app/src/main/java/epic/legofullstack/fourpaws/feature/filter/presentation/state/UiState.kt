package epic.legofullstack.fourpaws.feature.filter.presentation.state

import epic.legofullstack.fourpaws.core.domain.model.Area
import epic.legofullstack.fourpaws.core.presentation.model.ErrorModel

sealed class UiState {
    data class Content(val data: List<Area> = emptyList()): UiState()
    object Loading: UiState()
    data class Error(val errorModel: ErrorModel): UiState()
}