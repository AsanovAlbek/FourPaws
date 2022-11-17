package epic.legofullstack.fourpaws.feature.detail.data.local

import epic.legofullstack.fourpaws.application.local.dao.FavoritePetDao
import epic.legofullstack.fourpaws.feature.detail.data.mapper.toDetails
import epic.legofullstack.fourpaws.feature.detail.data.mapper.toEntity
import epic.legofullstack.fourpaws.feature.detail.data.model.PetDetailsDto
import epic.legofullstack.fourpaws.network.firebase.data.FirebaseDataSource

class DetailsDataSource(
    private val favoritesDao: FavoritePetDao,
    private val remoteSource: FirebaseDataSource
) {
    suspend fun addToFavorites(petDto: PetDetailsDto) {
        favoritesDao.addFavorite(petDto.toEntity())
    }
    suspend fun removeToFavorites(petDto: PetDetailsDto) {
        favoritesDao.removeFavorite(petDto.toEntity())
    }

    suspend fun findFavorite(id: Int) =
        if(favoritesDao.isExist(id))
            favoritesDao.findFavorite(id).toDetails()
        else
            remoteSource.getPetById(id)!!.toDetails()

    suspend fun isFavorite(petDto: PetDetailsDto) = favoritesDao.isExist(petDto.id)
}