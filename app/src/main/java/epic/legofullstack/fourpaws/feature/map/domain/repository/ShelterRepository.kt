package epic.legofullstack.fourpaws.feature.map.domain.repository

import epic.legofullstack.fourpaws.feature.map.data.model.ShelterDto

interface ShelterRepository {
    suspend fun getShelterByAreaId(areaId: Int): List<ShelterDto>
}