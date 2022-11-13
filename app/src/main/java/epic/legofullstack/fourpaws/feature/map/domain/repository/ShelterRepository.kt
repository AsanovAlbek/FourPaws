package epic.legofullstack.fourpaws.feature.map.domain.repository

import epic.legofullstack.fourpaws.network.firebase.data.model.ShelterPreviewDto

interface ShelterRepository {
    suspend fun getShelterByAreaId(areaId: Int): List<ShelterPreviewDto>
}