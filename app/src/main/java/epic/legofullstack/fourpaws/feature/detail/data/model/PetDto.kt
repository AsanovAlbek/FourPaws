package epic.legofullstack.fourpaws.feature.detail.data.model

import epic.legofullstack.fourpaws.feature.detail.domain.model.Shelter

data class PetDto(
    val petId : Int,
    val shelter: Shelter,
    val name : String,
    val isFavorite : Boolean = false,
    val city : String
) {
    fun addToFavorites() = PetDto(
        petId = petId,
        shelter = shelter,
        name = name,
        isFavorite = true,
        city = city
    )

    fun removeToFavorites() = PetDto(
        petId = petId,
        shelter = shelter,
        name = name,
        isFavorite = false,
        city = city
    )
}
