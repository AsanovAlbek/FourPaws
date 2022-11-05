package epic.legofullstack.fourpaws.feature.map.presentation.dto

import epic.legofullstack.fourpaws.core.presentation.model.ErrorModel
import epic.legofullstack.fourpaws.feature.map.presentation.item.ShelterMapItem

sealed class ShelterMapViewState {
    object Loading : ShelterMapViewState()
    data class Error(val errorModel: ErrorModel) : ShelterMapViewState()
    data class Content(val shelters: List<ShelterMapItem>) : ShelterMapViewState()
}
