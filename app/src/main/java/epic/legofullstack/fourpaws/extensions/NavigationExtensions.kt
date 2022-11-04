package epic.legofullstack.fourpaws.extensions

import androidx.annotation.IdRes
import androidx.annotation.MenuRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.google.android.material.appbar.MaterialToolbar
import epic.legofullstack.fourpaws.R

fun Fragment.activityNavController() = requireActivity().findNavController(R.id.nav_host_activity_main)

fun NavController.navigateSafely(@IdRes actionId: Int) {
    currentDestination?.getAction(actionId)?.let{navigate(actionId)}
}

fun NavController.navigateSafely(directions: NavDirections) {
    currentDestination?.getAction(directions.actionId)?.let { navigate(directions) }
}

fun Fragment.fragmentNavController() = requireActivity().findNavController(R.id.nav_host_fragment_main)

fun MaterialToolbar.refreshMenu(@MenuRes menuId: Int) {
    menu.clear()
    inflateMenu(menuId)
}