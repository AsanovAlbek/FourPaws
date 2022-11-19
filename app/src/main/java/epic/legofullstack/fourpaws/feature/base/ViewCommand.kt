package epic.legofullstack.fourpaws.feature.base

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.google.android.material.snackbar.Snackbar

interface ViewCommand

data class ShowToast(
    val message: String
) : ViewCommand

data class ShowDialog(
    val title: String,
    val message: String,
    val positiveButtonText: String?,
    val callbackPositiveButton: (() -> Unit)? = null,
    val isCancelable: Boolean = false
) : ViewCommand

data class ShowSnackbar(
    val text: String,
    val showTime: Int = Snackbar.LENGTH_LONG,
    @StringRes val actionTitle: Int? = null,
    val callback: (() -> Unit)? = null
) : ViewCommand

data class OpenFragment(
    val navController: NavController? = null,
    @IdRes val actionId: Int? = null,
    val directions: NavDirections? = null
) : ViewCommand

data class NavigateUp(val navController: NavController? = null): ViewCommand

data class StartActivityForMap(
    val longitude: Float,
    val latitude: Float
) : ViewCommand

data class CopyText(
    val label: String,
    val text: String
) : ViewCommand