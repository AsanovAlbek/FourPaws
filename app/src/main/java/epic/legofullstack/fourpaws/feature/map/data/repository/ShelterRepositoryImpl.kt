package epic.legofullstack.fourpaws.feature.map.data.repository

import epic.legofullstack.fourpaws.feature.map.domain.repository.ShelterRepository
import epic.legofullstack.fourpaws.network.firebase.data.FirebaseDataSource
import epic.legofullstack.fourpaws.network.firebase.data.model.ShelterPreviewDto

class ShelterRepositoryImpl(private val dataSource: FirebaseDataSource) : ShelterRepository {
    override suspend fun getShelterByAreaId(areaId: Int): List<ShelterPreviewDto> =
        dataSource.getObjectsByFieldName(FIELD_AREA_ID, areaId, SHELTER_COLLECTION, ShelterPreviewDto::class.java)

    companion object {
        private const val SHELTER_COLLECTION = "shelter"
        private const val FIELD_AREA_ID = "area.id"
    }
}