package epic.legofullstack.fourpaws.feature.detail.data.mapper

import epic.legofullstack.fourpaws.application.local.entity.FavoritePetEntity
import epic.legofullstack.fourpaws.core.domain.model.Area
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

fun FavoritePetEntity.toPetDto() =
    PetDto(
        petId = id,
        name = name,
        isFavorite = isFavorite,
        city = city,
        shelter = ShelterDto(
            id = shelterId,
            name = shelterName,
            address = address,
            email = email,
            phone = phone,
            area = Area(
                id = areaId,
                title = areaName
            )
        )
    )

fun PetDto.toEntity() =
    FavoritePetEntity(
        id = petId,
        name = name,
        shelterId = shelter.id,
        shelterName = shelter.name,
        areaId = shelter.area.id,
        areaName = shelter.area.title,
        address = shelter.address,
        email = shelter.email,
        phone = shelter.phone,
        isFavorite = isFavorite,
        city = city
    )