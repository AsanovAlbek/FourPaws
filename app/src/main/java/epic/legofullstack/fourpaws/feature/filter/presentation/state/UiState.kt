package epic.legofullstack.fourpaws.feature.filter.presentation.state

import epic.legofullstack.fourpaws.core.domain.model.Area
import epic.legofullstack.fourpaws.feature.filter.domain.model.PetFilterModel

sealed class UiState {
    data class Content(
        val areas: List<Area> = emptyList(),
        val userArea: String = "",
        val filter: PetFilterModel = PetFilterModel()
    ) : UiState()

    object Loading : UiState()
}