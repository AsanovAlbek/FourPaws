package epic.legofullstack.fourpaws.feature.filter.data.models

import epic.legofullstack.fourpaws.feature.filter.domain.model.PetFilterModel

fun PetFilterDto.toDomain() =
    PetFilterModel(
        areaId = areaId,
        petType = petType,
        gender = gender,
        age = age,
        characteristics = characteristics
    )

fun PetFilterModel.toDto() =
    PetFilterDto(
        areaId = areaId,
        petType = petType,
        gender = gender,
        age = age,
        characteristics = characteristics
    )