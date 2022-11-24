package epic.legofullstack.fourpaws.feature.filter.domain.repository

import epic.legofullstack.fourpaws.core.data.model.AreaDto

interface FilterRepository {
    suspend fun getAreas(): List<AreaDto>
}