package epic.legofullstack.fourpaws.feature.detail.data.mapper

import epic.legofullstack.fourpaws.feature.detail.data.model.PetDto
import epic.legofullstack.fourpaws.feature.detail.data.model.ShelterDto
import epic.legofullstack.fourpaws.feature.detail.domain.model.Pet
import epic.legofullstack.fourpaws.feature.detail.domain.model.Shelter

fun PetDto.toPet() = Pet(
    petId = petId,
    shelter = shelter.toShelter(),
    name = name,
    isFavorite = isFavorite,
    city = city
)

fun ShelterDto.toShelter() = Shelter(
    id = id,
    name = name,
    address=  address,
    area = area,
    phone =  phone,
    email = email
)

fun Pet.toPetDto() = PetDto(
    petId = petId,
    shelter = shelter.toShelterDto(),
    name = name,
    isFavorite = isFavorite,
    city = city
)

fun Shelter.toShelterDto() = ShelterDto(
    id = id,
    name = name,
    address=  address,
    area = area,
    phone =  phone,
    email = email
)