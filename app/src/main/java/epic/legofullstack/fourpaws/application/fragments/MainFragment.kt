package epic.legofullstack.fourpaws.application.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.FragmentMainBinding

class MainFragment :
    BaseNavigationFragment(R.layout.fragment_main, R.id.nav_host_fragment_main), MenuProvider {
    private val bindingView by viewBinding(FragmentMainBinding::bind)

    override fun setupNavigation() {
        bindingView.navViewMain.setupWithNavController(navController)
        setupToolbarMenu()
    }

    private fun setupToolbarMenu() {
        val menuHost : MenuHost = requireActivity() as MenuHost
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.home_toolbar_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean =
        when(menuItem.itemId) {
            R.id.mapMenuItem -> {
                //(TODO открыть карту с приютами)
                true }

            R.id.filterMenuItem -> {
                //(TODO открыть фильтр)
                true
            }
            else -> false
        }
}