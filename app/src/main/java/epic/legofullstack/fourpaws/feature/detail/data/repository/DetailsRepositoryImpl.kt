package epic.legofullstack.fourpaws.feature.detail.data.repository

import epic.legofullstack.fourpaws.feature.detail.data.local.DetailsDataSource
import epic.legofullstack.fourpaws.feature.detail.data.model.PetDetailsDto
import epic.legofullstack.fourpaws.feature.detail.domain.repository.DetailsRepository

class DetailsRepositoryImpl(
    private val source: DetailsDataSource,
): DetailsRepository {
    override suspend fun findPetById(id: Int) = source.findFavorite(id)

    /** Добавление [pet] во вкладку "Избранное" */
    override suspend fun addToFavorite(pet: PetDetailsDto) = source.addToFavorites(pet)

    /** Удаление [pet] из вкладки "Избранное" */
    override suspend fun removePetFromFavorite(pet: PetDetailsDto) = source.removeToFavorites(pet)

    override suspend fun isFavorite(pet: PetDetailsDto) = source.isFavorite(pet)
}