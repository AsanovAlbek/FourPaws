package epic.legofullstack.fourpaws.network.firebase.data.model

import epic.legofullstack.fourpaws.core.data.model.AreaDto

data class PetDto(
    val id: Int = 0,
    val shelter: ShelterDto = ShelterDto(),
    val name: String = "",
    val city: String = "",
    val age: Age = Age.UNKNOWN,
    val area: AreaDto = AreaDto(),
    val breed: PetBreed = PetBreed(),
    val color: String = "",
    val petType: PetType? = null,
    val gender: String = "",
    val imgs: List<String> = emptyList(),
    val previewImg: String = "",
    val description: String = "",
    val characteristics: List<String> = emptyList()
)

data class PetPreviewDto(
    val id: Int = 0,
    val name: String = "",
    val city: String = "",
    val gender: String = "",
    val previewImg: String = "",
)
