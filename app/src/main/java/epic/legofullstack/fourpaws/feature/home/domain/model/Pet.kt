package epic.legofullstack.fourpaws.feature.home.domain.model

/**
 * Domain модель питомца
 * @param id - id питомца
 * @param name - имя питомца
 */
data class Pet(
    val id: Int = 0,
    val name: String = "",
    val city: String = "",
    val gender: String = "",
    val previewImg: String = "",
)
