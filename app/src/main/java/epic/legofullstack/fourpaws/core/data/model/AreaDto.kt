package epic.legofullstack.fourpaws.core.data.model

import epic.legofullstack.fourpaws.core.domain.model.Area


data class AreaDto(
    val id: Int,
    val title: String
)

fun AreaDto.toArea() = Area(id = id, title = title)