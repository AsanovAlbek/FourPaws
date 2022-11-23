package epic.legofullstack.fourpaws.feature.filter.domain.model

import epic.legofullstack.fourpaws.core.domain.model.Area
import epic.legofullstack.fourpaws.network.firebase.data.model.Age
import epic.legofullstack.fourpaws.network.firebase.data.model.PetType

data class PetFilterModel(
    val area: Area = Area(),
    val petType: PetType? = null,
    val gender: String? = null,
    val age: Age? = null,
    val characteristics: List<String>? = null
)
