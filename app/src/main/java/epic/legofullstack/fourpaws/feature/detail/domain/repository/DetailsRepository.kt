package epic.legofullstack.fourpaws.feature.detail.domain.repository

import epic.legofullstack.fourpaws.feature.detail.data.model.PetDto
import epic.legofullstack.fourpaws.feature.detail.data.model.ShelterDto

interface DetailsRepository {
    suspend fun addToFavorite(pet: PetDto): PetDto
    suspend fun removePetFromFavorite(pet: PetDto): PetDto
    suspend fun call(shelter: ShelterDto)
    suspend fun sendMale(shelter: ShelterDto)
    suspend fun sharePet(pet: PetDto)
}