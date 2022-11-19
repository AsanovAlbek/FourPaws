package epic.legofullstack.fourpaws.feature.detail.data.model

import epic.legofullstack.fourpaws.core.domain.model.Area
import epic.legofullstack.fourpaws.network.firebase.data.model.Age
import epic.legofullstack.fourpaws.network.firebase.data.model.PetBreed
import epic.legofullstack.fourpaws.network.firebase.data.model.PetType

data class PetDetailsDto(
    val id: Int = 0,
    val shelter: ShelterDetailsDto = ShelterDetailsDto(),
    val name: String = "",
    val city: String = "",
    val age: Age = Age.UNKNOWN,
    val area: Area = Area(),
    val breed: PetBreed = PetBreed(),
    val color: String = "",
    val petType: PetType? = null,
    val gender: String = "",
    val imgs: List<String> = emptyList(),
    val previewImg: String = "",
    val description: String = "",
    val characteristics: List<String> = emptyList()
)
