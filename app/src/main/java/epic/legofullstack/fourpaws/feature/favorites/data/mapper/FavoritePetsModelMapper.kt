package epic.legofullstack.fourpaws.feature.favorites.data.mapper

import epic.legofullstack.fourpaws.feature.favorites.data.model.FavoritePetDto
import epic.legofullstack.fourpaws.feature.favorites.domain.model.FavoritePet

fun FavoritePetDto.toFavoritePet() =
    FavoritePet(id = id, name = name, isFavorite = isFavorite, city = city)