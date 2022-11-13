package epic.legofullstack.fourpaws.feature.detail.data.repository

import android.util.Log
import epic.legofullstack.fourpaws.application.local.dao.FavoritePetDao
import epic.legofullstack.fourpaws.feature.detail.data.local.DetailsLocalDataSource
import epic.legofullstack.fourpaws.feature.detail.data.mapper.toPetDto
import epic.legofullstack.fourpaws.feature.detail.data.model.PetDto
import epic.legofullstack.fourpaws.feature.detail.data.model.ShelterDto
import epic.legofullstack.fourpaws.feature.detail.domain.repository.DetailsRepository
import epic.legofullstack.fourpaws.feature.favorites.data.mapper.toFavoritePetDto

class DetailsRepositoryImpl(
    private val local: DetailsLocalDataSource,
): DetailsRepository {
    override suspend fun findPetById(id: Int): PetDto = local.findFavorite(id)

    /** Добавление [pet] во вкладку "Избранное" */
    override suspend fun addToFavorite(pet: PetDto) = local.addToFavorites(pet)

    /** Удаление [pet] из вкладки "Избранное" */
    override suspend fun removePetFromFavorite(pet: PetDto) = local.removeToFavorites(pet)

    companion object {
        const val EXIST = 1
        const val NOT_EXIST = 0
    }
}