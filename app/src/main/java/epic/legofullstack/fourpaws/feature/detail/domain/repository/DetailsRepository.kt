package epic.legofullstack.fourpaws.feature.detail.domain.repository

import epic.legofullstack.fourpaws.feature.detail.data.model.PetDetailsDto

interface DetailsRepository {
    suspend fun findPetById(id: Int): PetDetailsDto
    suspend fun addToFavorite(pet: PetDetailsDto)
    suspend fun removePetFromFavorite(pet: PetDetailsDto)
    suspend fun isFavorite(pet: PetDetailsDto): Boolean
}