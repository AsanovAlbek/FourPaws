package epic.legofullstack.fourpaws.feature.location.presentation.dto

import epic.legofullstack.fourpaws.core.domain.model.Area
import epic.legofullstack.fourpaws.core.presentation.model.ErrorModel

sealed class LocationViewState {
    object Loading : LocationViewState()
    data class Error(val errorModel: ErrorModel) : LocationViewState()
    data class Content(
        val userArea: String = "",
        val isIdentifyArea: Boolean = false,
        val areas: List<Area> = emptyList()
    ) : LocationViewState()
}