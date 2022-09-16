package epic.legofullstack.fourpaws.feature_home_page.domain.model

/**
 * Domain модель питомца
 * @param id - id питомца
 * @param name - имя питомца
 * @param isFavorite - является ли питомец в списке избранных
 */
data class Pet(
    val id : Int,
    val name : String,
    val isFavorite : Boolean = false
)
