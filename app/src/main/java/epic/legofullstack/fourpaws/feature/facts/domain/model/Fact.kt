package epic.legofullstack.fourpaws.feature.facts.domain.model

data class Fact(
    val id: Int = 0,
    val imgUrl: String = "",
    val title: String = "",
    val text: String = ""
){
    companion object{
        const val DEFAULT_FACT_ID = 0
    }
}

data class FactPreview(
    val id: Int,
    val imgUrl: String,
    val title: String
)
