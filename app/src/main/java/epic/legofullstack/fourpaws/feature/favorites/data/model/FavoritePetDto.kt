package epic.legofullstack.fourpaws.feature.favorites.data.model

/**
 * Класс, хранящий данные о питомцах в data слое
 * @param id - id питомца
 * @param name - кличка питомца
 */
data class FavoritePetDto (
    val id: Int = 0,
    val name: String = "",
    val city: String = "",
    val gender: String = "",
    val previewImg: String = "",
)