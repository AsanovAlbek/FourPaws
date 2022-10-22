package epic.legofullstack.fourpaws.feature.detail.data.model

import epic.legofullstack.fourpaws.core.domain.model.Area

data class ShelterDto(
    val id: Int,
    val name: String,
    val address: String,
    val area: Area,
    val phone: String,
    val email: String
)