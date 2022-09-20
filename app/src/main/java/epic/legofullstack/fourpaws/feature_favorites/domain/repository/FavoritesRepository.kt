package epic.legofullstack.fourpaws.feature_favorites.domain.repository

import epic.legofullstack.fourpaws.feature_favorites.data.model.FavoritePetDto

interface FavoritesRepository {
    suspend fun getFavorites() : List<FavoritePetDto>
}