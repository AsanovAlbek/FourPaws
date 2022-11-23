package epic.legofullstack.fourpaws.core.data.model

import epic.legofullstack.fourpaws.core.domain.model.PetFilter
import epic.legofullstack.fourpaws.network.firebase.data.model.Age
import epic.legofullstack.fourpaws.network.firebase.data.model.PetType

data class PetFilterDto(
    val area: AreaDto = AreaDto(),
    val petType: String? = null,
    val gender: String? = null,
    val age: String? = null,
    val characteristics: List<String>? = null
): RemoteDto<PetFilter> {
    override fun toDomain(): PetFilter =
        PetFilter(
            area = area.toDomain() ,
            petType = petType?.let { PetType.valueOf(it) },
            gender = gender,
            age = age?.let { Age.valueOf(it) },
            characteristics = characteristics
        )
}