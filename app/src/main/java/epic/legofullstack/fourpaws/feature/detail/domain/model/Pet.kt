package epic.legofullstack.fourpaws.feature.detail.domain.model

data class Pet(
    val petId : Int = 0,
    val shelter: Shelter = Shelter(),
    val name : String = "",
    val isFavorite : Boolean = false,
    val city : String = ""
) {
    fun addToFavorites() = copy(isFavorite = true)
    fun removeToFavorites() = copy(isFavorite = false)
}