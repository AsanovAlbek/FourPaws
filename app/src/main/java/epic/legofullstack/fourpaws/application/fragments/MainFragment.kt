package epic.legofullstack.fourpaws.application.fragments

import android.view.MenuItem
import androidx.core.view.children
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.FragmentMainBinding
import epic.legofullstack.fourpaws.extensions.fragmentNavController
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
            // Обработка нажатий на элементы нижнего меню
            navViewMain.setOnItemSelectedListener { item ->
                // Дефолтная обработка
                NavigationUI.onNavDestinationSelected(item, navController)
                // Изменение меню по выбранному элементу
                with(mainToolbar) {
                    when(item.itemId) {
                        R.id.navigation_home -> {
                            refreshMenu(R.menu.home_toolbar_menu)
                            true
                        }
                        else -> {
                            refreshMenu(R.menu.menu_with_arrow_button)
                            true
                        }
                    }
                }
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
                true
            }

            R.id.backArrowItem -> {
                navController.navigateUp()
                if (navController.currentDestination?.id == R.id.navigation_home) {
                    bindingView.mainToolbar.refreshMenu(R.menu.home_toolbar_menu)
                }
                true
            }

            else -> requireActivity().onOptionsItemSelected(menuItem)
        }
}