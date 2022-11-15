package epic.legofullstack.fourpaws.feature.home.data.model

/**
 * Модель для представления питомцев
 * @param petId - уникальный id питомца
 * @param name - кличка питомца
 * @param isFavorite - является ли питомец в списке избранных
 */
data class HomePetDto (
    val id: Int = 0,
    val name: String = "",
    val city: String = "",
    val gender: String = "",
    val previewImg: String = "",
)