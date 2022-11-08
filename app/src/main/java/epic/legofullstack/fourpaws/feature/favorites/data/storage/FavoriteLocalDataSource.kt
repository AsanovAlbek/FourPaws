package epic.legofullstack.fourpaws.feature.favorites.data.storage

import epic.legofullstack.fourpaws.feature.favorites.data.model.FavoritePetDto

class FavoriteLocalDataSource {
    suspend fun favoritePets() = fakeFavoriteList

    private val fakeFavoriteList = listOf(
        FavoritePetDto(1, "Мурка", true, "Ростов"),
        FavoritePetDto(2, "Тузик", true, "Саратов"),
        FavoritePetDto(3, "Мася", true,"Краснодар"),
        FavoritePetDto(4, "Рекс", true, "Нальчик"),
        FavoritePetDto(5, "Борис", true, "Москва")
    )
}