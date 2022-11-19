package epic.legofullstack.fourpaws.application.dto

import epic.legofullstack.fourpaws.core.domain.model.Area
import epic.legofullstack.fourpaws.core.presentation.model.ErrorModel

sealed class MainViewState {
    data class Error(val errorModel: ErrorModel) : MainViewState()
    data class Content(val userArea: Area) : MainViewState()
}