package epic.legofullstack.fourpaws.feature.home.presentation

import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.feature.home.presentation.dto.ErrorModel

fun Boolean.parseError(): ErrorModel =
    if (this){
        ErrorModel(
            icon = R.drawable.ic_sad,
            title = R.string.network_error
        )
    } else ErrorModel(
        icon = R.drawable.ic_sad,
        title = R.string.unknown_error
    )