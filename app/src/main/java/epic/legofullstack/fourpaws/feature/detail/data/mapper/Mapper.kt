package epic.legofullstack.fourpaws.feature.detail.data.mapper

import epic.legofullstack.fourpaws.application.local.entity.FavoritePetEntity
import epic.legofullstack.fourpaws.core.domain.model.Area
import epic.legofullstack.fourpaws.feature.detail.data.model.PetDetailsDto
import epic.legofullstack.fourpaws.feature.detail.data.model.ShelterDetailsDto
import epic.legofullstack.fourpaws.feature.detail.domain.model.PetDetail
import epic.legofullstack.fourpaws.feature.detail.domain.model.ShelterDetails
import epic.legofullstack.fourpaws.network.firebase.data.model.Age
import epic.legofullstack.fourpaws.network.firebase.data.model.PetBreed
import epic.legofullstack.fourpaws.network.firebase.data.model.PetDto
import epic.legofullstack.fourpaws.network.firebase.data.model.PetType
import epic.legofullstack.fourpaws.network.firebase.data.model.ShelterDto

fun PetDetailsDto.toPet() = PetDetail(
    id = id,
    shelter = shelter.toShelter(),
    name = name,
    city = city,
    age = age ,
    area = area,
    breed = breed,
    color = color,
    petType = petType,
    gender = gender,
    imgs = imgs,
    previewImg = previewImg,
    description = description,
    characteristics = characteristics
)

fun ShelterDetailsDto.toShelter() = ShelterDetails(
    id = id,
    name = name,
    address = address,
    area = area,
    city = city,
    phone = phone,
    email = email,
    longitude = longitude,
    latitude = latitude
)

fun PetDetail.toDetails() = PetDetailsDto(
    id = id,
    shelter = shelter.toDetails(),
    name = name,
    city = city,
    age = age ,
    area = area,
    breed = breed,
    color = color,
    petType = petType,
    gender = gender,
    imgs = imgs,
    previewImg = previewImg,
    description = description,
    characteristics = characteristics
)

fun ShelterDetails.toDetails() = ShelterDetailsDto(
    id = id,
    name = name,
    address=  address,
    area = area,
    phone =  phone,
    email = email
)

fun FavoritePetEntity.toDetails() =
    PetDetailsDto(
        id = id,
        name = name,
        city = city,
        shelter = ShelterDetailsDto(
            id = shelterId,
            name = shelterName,
            address = address,
            email = email,
            phone = phone,
            area = Area(id = areaId, title = areaName),
            longitude = longitude,
            latitude = latitude,
            city = city
        ),
        breed = PetBreed(id = breedId, name = breed),
        petType = petType.toPetType(),
        color = color,
        age = age.toAge(),
        gender = gender,
        imgs = images,
        previewImg = previewImageUrl,
        description = descriptions,
        characteristics = characteristics
    )

private fun String.toAge() = Age.values().first { this.compareTo(it.name) == 0 }
private fun String.toPetType() = PetType.values().first { this.compareTo(it.name) == 0 }

private fun ShelterDto.toDetails() =
    ShelterDetailsDto(
        id = id,
        name = name,
        address = address,
        email = email,
        area = area.toDomain(),
        phone = phone
    )

fun PetDetailsDto.toEntity() =
    FavoritePetEntity(
        id = id,
        name = name,
        shelterId = shelter.id,
        shelterName = shelter.name,
        areaId = shelter.area.id,
        areaName = shelter.area.title,
        address = shelter.address,
        email = shelter.email,
        phone = shelter.phone,
        latitude = shelter.latitude,
        longitude = shelter.longitude,
        city = city,
        breedId = breed.id,
        breed = breed.name,
        age = age.name,
        color = color,
        petType = petType?.name ?: "",
        gender = gender,
        images = imgs,
        previewImageUrl = previewImg,
        descriptions = description,
        characteristics = characteristics
    )

fun PetDto.toDetails() =
    PetDetailsDto(
        id = id,
        shelter = shelter.toDetails(),
        name = name,
        city = city,
        age = age ,
        area = area.toDomain(),
        breed = breed,
        color = color,
        petType = petType,
        gender = gender,
        imgs = imgs,
        previewImg = previewImg,
        description = description,
        characteristics = characteristics
    )