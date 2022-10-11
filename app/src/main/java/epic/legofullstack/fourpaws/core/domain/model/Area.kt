package epic.legofullstack.fourpaws.core.domain.model

import epic.legofullstack.fourpaws.core.data.model.AreaDto

data class Area(val id: Int, val title: String)

fun Area.toAreaModel() = AreaDto(id = this.id, title = this.title)
