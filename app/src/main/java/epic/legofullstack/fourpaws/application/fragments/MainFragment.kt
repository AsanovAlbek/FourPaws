package epic.legofullstack.fourpaws.application.fragments

import android.view.MenuItem
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.FragmentMainBinding
import epic.legofullstack.fourpaws.extensions.fragmentNavController

class MainFragment :
    BaseNavigationFragment(R.layout.fragment_main, R.id.nav_host_fragment_main) {
    private val bindingView by viewBinding(FragmentMainBinding::bind)

    override fun setupNavigation() {
        bindingView.navViewMain.setupWithNavController(navController)
        setupToolbarMenu()
    }

    private fun setupToolbarMenu() {
        bindingView.mainToolbar.apply {
            setOnMenuItemClickListener(::handleMenuItemClick)
        }
    }

    private fun handleMenuItemClick(menuItem: MenuItem): Boolean =
        when (menuItem.itemId) {
            R.id.mapMenuItem -> {
                fragmentNavController().navigate(R.id.action_navigation_home_to_shelter_map_fragment)
                true
            }

            R.id.filterMenuItem -> {
                //(TODO открыть фильтр)
                true
            }
            else -> requireActivity().onOptionsItemSelected(menuItem)
        }
}