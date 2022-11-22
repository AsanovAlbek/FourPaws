package epic.legofullstack.fourpaws.feature.filter.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PetFilterModel(
    val areaId: Int? = null,
    val petType: String? = null,
    val gender: String? = null,
    val age: String? = null,
    val characteristics: List<String>? = null
): Parcelable
