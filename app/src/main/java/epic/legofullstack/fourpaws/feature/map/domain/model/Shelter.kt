package epic.legofullstack.fourpaws.feature.map.domain.model

data class Shelter(
    val id: Int,
    val name: String,
    val address: String,
    val longitude: Float,
    val latitude: Float
)
