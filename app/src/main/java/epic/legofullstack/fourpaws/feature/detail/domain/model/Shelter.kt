package epic.legofullstack.fourpaws.feature.detail.domain.model

import epic.legofullstack.fourpaws.core.domain.model.Area

data class Shelter(
    val id: Int = 0,
    val name: String = "",
    val address: String = "",
    val area: Area = Area(0,""),
    val phone: String = "",
    val email: String = ""
)