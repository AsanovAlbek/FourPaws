package epic.legofullstack.fourpaws.feature.home.data.model

/**
 * Модель для представления питомцев
 * @param petId - уникальный id питомца
 * @param name - кличка питомца
 * @param isFavorite - является ли питомец в списке избранных
 */
data class PetDto (
    val petId : Int,
    val name : String,
    val isFavorite : Boolean = false,
    val city : String
)