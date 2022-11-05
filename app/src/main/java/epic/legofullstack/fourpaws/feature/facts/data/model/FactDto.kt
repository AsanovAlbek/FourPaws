package epic.legofullstack.fourpaws.feature.facts.data.model

import epic.legofullstack.fourpaws.core.data.model.RemoteDto
import epic.legofullstack.fourpaws.feature.facts.domain.model.Fact
import epic.legofullstack.fourpaws.feature.facts.domain.model.FactPreview

data class FactDto(
    val id: Int,
    val imgUrl: String,
    val title: String,
    val text: String
) : RemoteDto<Fact> {
    override fun toDomain(): Fact =
        Fact(
            id = this.id,
            imgUrl = this.imgUrl,
            title = this.title,
            text = this.text
        )
}

data class FactPreviewDto(
    val id: Int,
    val imgUrl: String,
    val title: String
) : RemoteDto<FactPreview> {
    override fun toDomain(): FactPreview =
        FactPreview(
            id = this.id,
            imgUrl = this.imgUrl,
            title = this.title
        )
}