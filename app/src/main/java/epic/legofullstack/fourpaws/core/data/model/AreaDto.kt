package epic.legofullstack.fourpaws.core.data.model

import epic.legofullstack.fourpaws.core.domain.model.Area


data class AreaDto(
    val id: Int,
    val title: String
) : RemoteDto<Area> {
    override fun toDomain(): Area = Area(id = id, title = title)
}