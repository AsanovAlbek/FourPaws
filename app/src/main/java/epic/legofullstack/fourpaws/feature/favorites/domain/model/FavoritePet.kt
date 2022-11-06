package epic.legofullstack.fourpaws.feature.favorites.domain.model

data class FavoritePet (
    val id : Int,
    val name : String,
    val isFavorite : Boolean = false,
    val city : String
)