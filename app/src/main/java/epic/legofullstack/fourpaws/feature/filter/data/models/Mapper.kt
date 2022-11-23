package epic.legofullstack.fourpaws.feature.filter.data.models

import epic.legofullstack.fourpaws.core.domain.model.PetFilter
import epic.legofullstack.fourpaws.feature.filter.domain.model.PetFilterModel

fun PetFilter.toFilterModel() =
    PetFilterModel(
        area = area,
        petType = petType,
        gender = gender,
        age = age,
        characteristics = characteristics
    )

fun PetFilterModel.toSaveFilter() =
    PetFilter(
        area = this.area,
                petType = this.petType,
                gender = this.gender,
                age = this.age,
                characteristics = this.characteristics
    )