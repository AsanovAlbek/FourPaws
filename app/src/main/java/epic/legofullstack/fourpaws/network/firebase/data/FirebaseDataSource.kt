package epic.legofullstack.fourpaws.network.firebase.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import epic.legofullstack.fourpaws.core.data.model.AreaDto
import epic.legofullstack.fourpaws.network.firebase.data.model.PetDto
import epic.legofullstack.fourpaws.network.firebase.data.model.PetPreviewDto
import epic.legofullstack.fourpaws.network.firebase.data.model.ShelterDto
import kotlinx.coroutines.tasks.await

class FirebaseDataSource(private val firestore: FirebaseFirestore) {

    suspend fun <T> getObjects(nameCollection: String, resultClassName: Class<T>): List<T> =
        firestore.collection(nameCollection)
            .get()
            .await()
            .toObjects(resultClassName)

    suspend fun <T> getObjectById(id: Int, nameCollection: String, resultClassName: Class<T>): T? =
        firestore.collection(nameCollection)
            .whereEqualTo("id", id)
            .get()
            .await()
            .toObjects(resultClassName)
            .firstOrNull()

    suspend fun <T> getObjectsByFieldName(fieldName: String, value: Int, nameCollection: String, resultClassName: Class<T>): List<T> =
        firestore.collection(nameCollection)
            .whereEqualTo(fieldName, value)
            .get()
            .await()
            .toObjects(resultClassName)

    suspend fun getPetById(petId: Int): PetDto? =
        firestore.collection(PET_COLLECTION)
            .whereEqualTo("id", petId)
            .get()
            .await()
            .firstOrNull()
            ?.let { document ->
                val shelterDto = document.getLong("shelterId")
                    ?.let { getObjectById(it.toInt(), SHELTER_COLLECTION, ShelterDto::class.java) }

                val areaDto = document.getLong("areaId")
                    ?.let { getObjectById(it.toInt(), AREA_COLLECTION, AreaDto::class.java) }

                val petDto = document.toObject(PetDto::class.java)
                return petDto.copy(
                    area = areaDto ?: AreaDto(),
                    shelter = shelterDto ?: ShelterDto()
                )
            }

    suspend fun petFilter(areaId: Int, filter: PetFilter? = null): List<PetPreviewDto> {
        var queryPet: Query = firestore.collection(PET_COLLECTION)
            .whereEqualTo("areaId", areaId)

        if (filter != null) {
            if (filter.petType != null) {
                queryPet = queryPet.whereEqualTo("petType", filter.petType)
            }
            if (filter.age != null) {
                queryPet = queryPet.whereEqualTo("age", filter.age)
            }
            if (filter.gender != null && filter.gender.trim().isNotEmpty()) {
                queryPet = queryPet.whereEqualTo("gender", filter.gender)
            }
            if (filter.city != null && filter.city.trim().isNotEmpty()) {
                queryPet = queryPet.whereEqualTo("city", filter.city)
            }
            if (filter.characteristics != null && filter.characteristics.isNotEmpty()) {
                queryPet = queryPet.whereArrayContainsAny("characteristics", filter.characteristics)
            }
        }

        return queryPet.get().await().toObjects(PetPreviewDto::class.java)
    }

    companion object {
        private const val AREA_COLLECTION = "area"
        private const val SHELTER_COLLECTION = "shelter"
        private const val PET_COLLECTION = "pet"
    }
}