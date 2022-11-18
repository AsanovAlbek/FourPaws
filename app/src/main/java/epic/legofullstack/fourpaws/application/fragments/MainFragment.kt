package epic.legofullstack.fourpaws.application.fragments

import android.view.MenuItem
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.FragmentMainBinding
import epic.legofullstack.fourpaws.extensions.fragmentNavController
import epic.legofullstack.fourpaws.extensions.navigateSafely
import epic.legofullstack.fourpaws.extensions.refreshMenu

class MainFragment :
    BaseNavigationFragment(R.layout.fragment_main, R.id.nav_host_fragment_main) {
    private val bindingView by viewBinding(FragmentMainBinding::bind)

    override fun setupNavigation() {
        bindingView.navViewMain.setupWithNavController(navController)
        setupToolbarMenu()
    }

    private fun setupToolbarMenu() {
        with(bindingView) {
            mainToolbar.setOnMenuItemClickListener(::homeMenuItemClick)
            mainToolbar.inflateMenu(R.menu.home_toolbar_menu)

            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.navigation_home -> {
                        mainToolbar.navigationIcon = null
                        mainToolbar.refreshMenu(R.menu.home_toolbar_menu)
                    }
                    R.id.mapMenuItem -> refreshToolbar(filterVisible = true, backButtonVisible = true)
                    R.id.filterMenuItem -> refreshToolbar(backButtonVisible = true)
                    else -> refreshToolbar()
                }
                mainToolbar.title = destination.label
            }
        }
    }

    private fun homeMenuItemClick(menuItem: MenuItem): Boolean =
        when(menuItem.itemId) {
            R.id.mapMenuItem -> {
                fragmentNavController().navigate(R.id.action_navigation_home_to_shelter_map_fragment)
                true
            }
            R.id.filterMenuItem -> {
                // todo переход в фильтр
                if (fragmentNavController().currentDestination?.id == R.id.navigation_home) {
                    fragmentNavController().navigateSafely(R.id.action_navigation_home_to_filterPetFragment)
                } else {
                    fragmentNavController().navigateSafely(R.id.action_navigation_mapMenuItem_to_filterPetFragment)
                }
                true
            }
            else -> requireActivity().onOptionsItemSelected(menuItem)
        }

    private fun refreshToolbar(
        filterVisible: Boolean = false,
        mapVisible: Boolean = false,
        backButtonVisible: Boolean = false
    ) {
        with(bindingView.mainToolbar) {
            menu.findItem(R.id.filterMenuItem).isVisible = filterVisible
            menu.findItem(R.id.mapMenuItem).isVisible = mapVisible
            if (backButtonVisible) {
                setNavigationIcon(R.drawable.ic_arrow_back)
                setNavigationOnClickListener {
                    fragmentNavController().navigateUp()
                }
            }
        }
    }
}