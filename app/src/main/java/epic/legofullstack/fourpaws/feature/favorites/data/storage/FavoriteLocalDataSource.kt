package epic.legofullstack.fourpaws.feature.favorites.data.storage

import android.util.Log
import epic.legofullstack.fourpaws.application.local.dao.FavoritePetDao
import epic.legofullstack.fourpaws.feature.favorites.data.mapper.toFavoritePetDto
import epic.legofullstack.fourpaws.feature.favorites.data.model.FavoritePetDto

class FavoriteLocalDataSource(
    private val favoritesDao: FavoritePetDao
) {
    suspend fun favoritePets(): List<FavoritePetDto> =
        favoritesDao.getFavorites().map { entity -> entity.toFavoritePetDto() }
}