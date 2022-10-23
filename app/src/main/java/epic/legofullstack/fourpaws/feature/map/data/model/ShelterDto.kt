package epic.legofullstack.fourpaws.feature.map.data.model

import epic.legofullstack.fourpaws.core.data.model.RemoteDto
import epic.legofullstack.fourpaws.core.domain.model.Area
import epic.legofullstack.fourpaws.feature.map.domain.model.Shelter

data class ShelterDto(
    val id: Int,
    val name: String,
    val address: String,
    val area: Area,
    val phone: String,
    val email: String?,
    val longitude: Float?,
    val latitude: Float?
) : RemoteDto<Shelter> {
    override fun toDomain(): Shelter = Shelter(
        id = this.id,
        name = this.name,
        address = this.address,
        longitude = this.longitude,
        latitude = this.latitude
    )
}