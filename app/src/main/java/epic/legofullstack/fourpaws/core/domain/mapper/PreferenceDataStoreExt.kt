package epic.legofullstack.fourpaws.core.domain.mapper

import epic.legofullstack.fourpaws.PetFilterPreferences
import epic.legofullstack.fourpaws.UserAreaPreferences
import epic.legofullstack.fourpaws.core.domain.model.Area
import epic.legofullstack.fourpaws.core.domain.model.PetFilter
import epic.legofullstack.fourpaws.network.firebase.data.model.Age
import epic.legofullstack.fourpaws.network.firebase.data.model.PetType

fun UserAreaPreferences.toArea() = Area(id = this.id, title = this.title)

fun PetFilterPreferences.toPetFilter() = PetFilter(
    area = this.area.toArea(),
    petType = if(this.petType.isEmpty()) null else PetType.valueOf(this.petType),
    gender = this.gender,
    age = if(this.age.isEmpty()) Age.UNKNOWN else Age.valueOf(this.age),
    characteristics = this.characteristicsList
)