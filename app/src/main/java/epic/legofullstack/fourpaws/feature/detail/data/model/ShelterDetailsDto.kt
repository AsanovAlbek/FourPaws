package epic.legofullstack.fourpaws.feature.detail.data.model

import epic.legofullstack.fourpaws.core.data.model.AreaDto
import epic.legofullstack.fourpaws.core.domain.model.Area

data class ShelterDetailsDto(
    val id: Int = 0,
    val name: String = "",
    val address: String = "",
    val area: Area = Area(),
    val city: String = "",
    val phone: String = "",
    val email: String = "",
    val longitude: Float = 0f,
    val latitude: Float = 0f
)