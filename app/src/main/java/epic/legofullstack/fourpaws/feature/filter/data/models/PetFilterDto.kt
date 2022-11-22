package epic.legofullstack.fourpaws.feature.filter.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class PetFilterDto(
    val areaId: Int? = null,
    val petType: String? = null,
    val gender: String? = null,
    val age: String? = null,
    val characteristics: List<String>? = null
)