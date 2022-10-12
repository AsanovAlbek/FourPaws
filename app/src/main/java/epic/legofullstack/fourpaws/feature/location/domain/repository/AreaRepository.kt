package epic.legofullstack.fourpaws.feature.location.domain.repository

import epic.legofullstack.fourpaws.core.data.model.AreaDto

interface AreaRepository {
    suspend fun getAreas(): List<AreaDto>
}