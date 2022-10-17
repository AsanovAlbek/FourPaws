package epic.legofullstack.fourpaws.feature.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import epic.legofullstack.fourpaws.extensions.navigateSafely

@AndroidEntryPoint
open class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    val baseViewModel: BaseViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        baseViewModel.commands.observe(viewLifecycleOwner, ::handleCommand)
    }

    protected open fun handleCommand(command: ViewCommand) {
        when (command) {
            is ShowToast -> showToast(command)
            is ShowDialog -> showMaterialAlertDialog(command)
            is ShowSnackbar -> showSnackbar(command)
            is OpenFragment -> goToFragment(command)
        }
    }

    private fun goToFragment(command: OpenFragment) {
        if (command.actionId != null) {
            command.navController.navigateSafely(command.actionId)
        } else {
            command.directions?.let { command.navController.navigateSafely(it) }
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
}