package epic.legofullstack.fourpaws.feature.facts.presentation.dto

import epic.legofullstack.fourpaws.core.presentation.model.ErrorModel
import epic.legofullstack.fourpaws.feature.facts.domain.model.Fact

sealed class FactViewState {
    object Loading : FactViewState()
    data class Error(val errorModel: ErrorModel) : FactViewState()
    data class Content(val facts: List<ItemFact> = emptyList()) : FactViewState()
    data class DetailContent(val fact: Fact) : FactViewState()
}
