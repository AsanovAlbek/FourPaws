package epic.legofullstack.fourpaws.feature_favorites.data.repository

import epic.legofullstack.fourpaws.feature_favorites.data.model.FavoritePetDto
import epic.legofullstack.fourpaws.feature_favorites.data.storage.LocalDataSource
import epic.legofullstack.fourpaws.feature_favorites.domain.repository.FavoritesRepository

class FavoritesRepositoryImpl(
    private val localDataSource: LocalDataSource
): FavoritesRepository {
    override suspend fun getFavorites(): List<FavoritePetDto> =
        localDataSource.favoritePets()
}