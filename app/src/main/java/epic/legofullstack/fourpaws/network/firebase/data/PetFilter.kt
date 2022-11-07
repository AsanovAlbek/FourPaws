package epic.legofullstack.fourpaws.network.firebase.data

import epic.legofullstack.fourpaws.network.firebase.data.model.Age
import epic.legofullstack.fourpaws.network.firebase.data.model.PetType

data class PetFilter(
  val petType: PetType? = null,
  val gender: String? = null,
  val age: Age? = null,
  val city: String? = null,
  val characteristics: List<String>? = null
)
