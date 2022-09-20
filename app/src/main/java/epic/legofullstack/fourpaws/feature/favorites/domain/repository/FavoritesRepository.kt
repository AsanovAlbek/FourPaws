package epic.legofullstack.fourpaws.feature.favorites.domain.repository

import epic.legofullstack.fourpaws.feature.favorites.data.model.FavoritePetDto

interface FavoritesRepository {
    suspend fun getFavorites() : List<FavoritePetDto>
}