package epic.legofullstack.fourpaws.feature.facts.data.repository

import epic.legofullstack.fourpaws.feature.facts.domain.repository.FactRepository
import epic.legofullstack.fourpaws.network.firebase.data.FirebaseDataSource
import epic.legofullstack.fourpaws.network.firebase.data.model.FactDto
import epic.legofullstack.fourpaws.network.firebase.data.model.FactPreviewDto

class FactRepositoryImpl(private val firebaseDataSource: FirebaseDataSource) : FactRepository {
    override suspend fun getFacts(): List<FactPreviewDto> = firebaseDataSource.getObjects(FACT_COLLECTION, FactPreviewDto::class.java)

    override suspend fun getFactById(factId: Int): FactDto? {
        return firebaseDataSource.getObjectById(factId, FACT_COLLECTION, FactDto::class.java)
    }

    companion object {
        private const val FACT_COLLECTION = "fact"
    }
}