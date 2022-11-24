package epic.legofullstack.fourpaws.core.domain.model

import epic.legofullstack.fourpaws.core.data.model.PetFilterDto
import epic.legofullstack.fourpaws.network.firebase.data.model.Age
import epic.legofullstack.fourpaws.network.firebase.data.model.PetType

data class PetFilter(
  val area: Area = Area(),
  val petType: PetType? = null,
  val gender: String? = null,
  val age: Age? = null,
  val city: String? = null,
  val characteristics: List<String>? = null
) {

  fun toPetFilterDto() =  PetFilterDto(
    area = this.area.toAreaModel(),
    petType = this.petType?.name,
    gender = this.gender,
    age = this.age?.name,
    characteristics = this.characteristics
  )
}
