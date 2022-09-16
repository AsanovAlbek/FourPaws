package epic.legofullstack.fourpaws.feature_home_page.data.mapper

import epic.legofullstack.fourpaws.feature_home_page.data.model.PetDto
import epic.legofullstack.fourpaws.feature_home_page.domain.model.Pet

fun PetDto.toPet() = Pet(id = petId, name = name, isFavorite = isFavorite)