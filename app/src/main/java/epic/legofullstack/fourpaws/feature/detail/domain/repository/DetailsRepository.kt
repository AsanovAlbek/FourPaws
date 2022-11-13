package epic.legofullstack.fourpaws.feature.detail.domain.repository

import epic.legofullstack.fourpaws.feature.detail.data.model.PetDto
import epic.legofullstack.fourpaws.feature.detail.data.model.ShelterDto

interface DetailsRepository {
    suspend fun findPetById(id: Int): PetDto
    suspend fun addToFavorite(pet: PetDto)
    suspend fun removePetFromFavorite(pet: PetDto)
}