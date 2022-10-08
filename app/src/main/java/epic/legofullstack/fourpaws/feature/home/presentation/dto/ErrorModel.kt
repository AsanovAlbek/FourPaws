package epic.legofullstack.fourpaws.feature.home.presentation.dto

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ErrorModel(@StringRes val title : Int, @DrawableRes val icon : Int)