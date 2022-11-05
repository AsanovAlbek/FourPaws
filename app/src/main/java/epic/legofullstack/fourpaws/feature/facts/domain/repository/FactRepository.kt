package epic.legofullstack.fourpaws.feature.facts.domain.repository

import epic.legofullstack.fourpaws.feature.facts.data.model.FactDto
import epic.legofullstack.fourpaws.feature.facts.data.model.FactPreviewDto

interface FactRepository {
    suspend fun getFacts(): List<FactPreviewDto>

    suspend fun getFactById(factId: Int): FactDto
}
