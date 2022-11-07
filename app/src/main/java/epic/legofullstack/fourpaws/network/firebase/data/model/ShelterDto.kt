package epic.legofullstack.fourpaws.network.firebase.data.model

import epic.legofullstack.fourpaws.core.data.model.AreaDto

data class ShelterPreviewDto(
    val id: Int = 0,
    val name: String = "",
    val address: String = "",
    val longitude: Float = 0f,
    val latitude: Float = 0f
)

data class ShelterDto(
    val id: Int = 0,
    val name: String = "",
    val address: String = "",
    val area: AreaDto = AreaDto(),
    val city: String = "",
    val phone: String = "",
    val email: String = "",
    val longitude: Float = 0f,
    val latitude: Float = 0f
)
