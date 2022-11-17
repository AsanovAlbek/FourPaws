package epic.legofullstack.fourpaws.feature.detail.domain.model

import epic.legofullstack.fourpaws.core.domain.model.Area

data class ShelterDetails(
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