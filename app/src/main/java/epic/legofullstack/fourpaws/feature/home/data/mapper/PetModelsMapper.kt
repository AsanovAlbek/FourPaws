package epic.legofullstack.fourpaws.feature.home.data.mapper

import epic.legofullstack.fourpaws.feature.home.data.model.PetDto
import epic.legofullstack.fourpaws.feature.home.domain.model.Pet

fun PetDto.toPet() = Pet(id = petId, name = name, isFavorite = isFavorite, city = city)