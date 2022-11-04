package epic.legofullstack.fourpaws.feature.detail.data.model

data class PetDto(
    val petId: Int,
    val shelter: ShelterDto,
    val name: String,
    val isFavorite: Boolean = false,
    val city: String
) {
    fun addToFavorites() = copy(isFavorite = true)
    fun removeToFavorites() = copy(isFavorite = false)
}
