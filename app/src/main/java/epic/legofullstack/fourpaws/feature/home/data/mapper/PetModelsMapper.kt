package epic.legofullstack.fourpaws.feature.home.data.mapper

import epic.legofullstack.fourpaws.feature.home.data.model.HomePetDto
import epic.legofullstack.fourpaws.feature.home.domain.model.Pet
import epic.legofullstack.fourpaws.network.firebase.data.model.PetDto

fun HomePetDto.toPet() = Pet(
    id = id,
    name = name,
    city = city,
    gender = gender,
    previewImg = previewImg
)

fun PetDto.toHome() = HomePetDto(
    id = id,
    name = name,
    city = city,
    gender = gender,
    previewImg = previewImg
)