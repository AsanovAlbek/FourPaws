package epic.legofullstack.fourpaws.feature.favorites.data.mapper

import epic.legofullstack.fourpaws.application.local.entity.FavoritePetEntity
import epic.legofullstack.fourpaws.feature.favorites.data.model.FavoritePetDto
import epic.legofullstack.fourpaws.feature.favorites.domain.model.FavoritePet

fun FavoritePetDto.toFavoritePet() =
    FavoritePet(id = id, name = name, previewImg = previewImg, city = city, gender = gender)

fun FavoritePetEntity.toFavoritePetDto() =
    FavoritePetDto(id = id, name = name, previewImg = previewImageUrl, city = city, gender = gender)