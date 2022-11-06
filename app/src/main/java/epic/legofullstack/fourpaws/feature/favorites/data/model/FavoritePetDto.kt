package epic.legofullstack.fourpaws.feature.favorites.data.model

/**
 * Класс, хранящий данные о питомцах в data слое
 * @param id - id питомца
 * @param name - кличка питомца
 * @param isFavorite - находится ли он в "избранном"
 */
data class FavoritePetDto (
    val id : Int,
    val name : String,
    val isFavorite : Boolean = false,
    val city : String
)