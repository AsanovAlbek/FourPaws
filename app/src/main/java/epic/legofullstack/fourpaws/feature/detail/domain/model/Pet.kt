package epic.legofullstack.fourpaws.feature.detail.domain.model

data class Pet(
    val petId : Int,
    val shelter: Shelter,
    val name : String,
    val isFavorite : Boolean = false,
    val city : String
)