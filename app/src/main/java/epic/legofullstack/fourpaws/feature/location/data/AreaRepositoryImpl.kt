package epic.legofullstack.fourpaws.feature.location.data

import epic.legofullstack.fourpaws.core.data.model.AreaDto
import epic.legofullstack.fourpaws.feature.location.domain.repository.AreaRepository
import epic.legofullstack.fourpaws.network.firebase.data.FirebaseDataSource

class AreaRepositoryImpl(
    private val dataSource: FirebaseDataSource
) : AreaRepository {
    override suspend fun getAreas(): List<AreaDto> = dataSource.getObjects(AREA_COLLECTION, AreaDto::class.java)

    companion object {
        private const val AREA_COLLECTION = "area"
    }
}