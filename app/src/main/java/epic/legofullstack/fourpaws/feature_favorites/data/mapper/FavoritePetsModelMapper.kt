package epic.legofullstack.fourpaws.feature_favorites.data.mapper

import epic.legofullstack.fourpaws.feature_favorites.data.model.FavoritePetDto
import epic.legofullstack.fourpaws.feature_favorites.domain.model.FavoritePet

fun FavoritePetDto.toFavoritePet() =
    FavoritePet(id, name, isFavorite)