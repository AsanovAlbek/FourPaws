package epic.legofullstack.fourpaws.core.presentation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ErrorModel(@StringRes val title : Int, @DrawableRes val icon : Int)