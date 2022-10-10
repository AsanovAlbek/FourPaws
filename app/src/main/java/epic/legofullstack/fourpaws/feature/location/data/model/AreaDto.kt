package epic.legofullstack.fourpaws.feature.location.data.model

import epic.legofullstack.fourpaws.feature.location.domain.model.Area


data class AreaDto(
    val id: Int,
    val title: String
)

fun AreaDto.toArea() = Area(id = id, title = title)