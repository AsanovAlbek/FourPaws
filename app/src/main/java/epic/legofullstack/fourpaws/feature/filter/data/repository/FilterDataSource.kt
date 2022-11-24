package epic.legofullstack.fourpaws.feature.filter.data.repository

import epic.legofullstack.fourpaws.core.data.model.AreaDto
import epic.legofullstack.fourpaws.network.firebase.data.FirebaseDataSource

class FilterDataSource(
    private val remoteDataSource: FirebaseDataSource
) {
    suspend fun getAreas(): List<AreaDto> =
        remoteDataSource.getObjects(AREA_COLLECTION, AreaDto::class.java)

    companion object {
        private const val AREA_COLLECTION = "area"
    }
}