package epic.legofullstack.fourpaws.feature.map.domain.mapper

import epic.legofullstack.fourpaws.feature.map.domain.model.Shelter
import epic.legofullstack.fourpaws.network.firebase.data.model.ShelterPreviewDto

fun ShelterPreviewDto.toShelter(): Shelter =
    Shelter(
        id = this.id,
        name = this.name,
        address = this.address,
        longitude = this.longitude,
        latitude = this.latitude
    )