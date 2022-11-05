package epic.legofullstack.fourpaws.feature.base

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import epic.legofullstack.fourpaws.extensions.fragmentNavController
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.extensions.navigateSafely

@AndroidEntryPoint
open class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    val baseViewModel: BaseViewModel by viewModels()

    protected open fun handleCommand(command: ViewCommand) {
        when (command) {
            is ShowToast -> showToast(command)
            is ShowDialog -> showMaterialAlertDialog(command)
            is ShowSnackbar -> showSnackbar(command)
            is OpenFragment -> goToFragment(command)
            is StartActivityForMap -> startActivityForMap(command.longitude, command.latitude)
        }
    }

    private fun goToFragment(command: OpenFragment) {
        val currentNavController = command.navController ?: fragmentNavController()
        if (command.actionId != null) {
            currentNavController.navigateSafely(command.actionId)
        } else {
            command.directions?.let { currentNavController.navigateSafely(it) }
        }
    }

    private fun showSnackbar(command: ShowSnackbar) {
        val snackbar = Snackbar.make(requireView(), command.text, command.showTime)
        command.actionTitle?.let { title -> snackbar.setAction(title) { command.callback?.invoke() } }
        snackbar.show()
    }

    private fun showMaterialAlertDialog(command: ShowDialog) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(command.title)
            .setMessage(command.message)
            .setPositiveButton(command.positiveButtonText) { _, _ -> command.callbackPositiveButton?.invoke() }
            .setCancelable(command.isCancelable)
            .show()
    }

    private fun showToast(command: ShowToast) {
        Toast.makeText(requireContext(), command.message, Toast.LENGTH_LONG).show()
    }

    private fun startActivityForMap(longitude: Float, latitude: Float) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(getString(R.string.uri_geo, latitude, longitude))
        )
        intent.setPackage(getString(R.string.package_maps))
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        } else {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.uri_market))))
        }
    }
}