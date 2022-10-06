package epic.legofullstack.fourpaws.feature.favorites.data.storage

import epic.legofullstack.fourpaws.feature.favorites.data.model.FavoritePetDto

class FavoriteLocalDataSource {
    suspend fun favoritePets() = fakeFavoriteList

    private val fakeFavoriteList = listOf(
        FavoritePetDto(1, "Мурка", true),
        FavoritePetDto(2, "Тузик", true),
        FavoritePetDto(3, "Мася"),
        FavoritePetDto(4, "Рекс"),
        FavoritePetDto(5, "Борис", true)
    )
}