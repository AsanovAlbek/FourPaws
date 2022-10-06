package epic.legofullstack.fourpaws.feature.favorites.data.repository

import epic.legofullstack.fourpaws.feature.favorites.data.model.FavoritePetDto
import epic.legofullstack.fourpaws.feature.favorites.data.storage.FavoriteLocalDataSource
import epic.legofullstack.fourpaws.feature.favorites.domain.repository.FavoritesRepository

class FavoritesRepositoryImpl(
    private val localDataSource: FavoriteLocalDataSource
): FavoritesRepository {
    override suspend fun getFavorites(): List<FavoritePetDto> =
        localDataSource.favoritePets()
}