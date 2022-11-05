package epic.legofullstack.fourpaws.feature.facts.domain.model

data class Fact(
    val id: Int,
    val imgUrl: String,
    val title: String,
    val text: String
)

data class FactPreview(
    val id: Int,
    val imgUrl: String,
    val title: String
)
