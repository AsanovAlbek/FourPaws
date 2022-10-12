package epic.legofullstack.fourpaws.core.domain.mapper

import epic.legofullstack.fourpaws.UserAreaPreferences
import epic.legofullstack.fourpaws.core.domain.model.Area

fun UserAreaPreferences.toArea() = Area(id = this.id, title = this.title)