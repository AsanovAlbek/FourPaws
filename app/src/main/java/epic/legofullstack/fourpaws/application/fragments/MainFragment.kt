package epic.legofullstack.fourpaws.application.fragments

import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.FragmentMainBinding

class MainFragment : BaseNavigationFragment(R.layout.fragment_main, R.id.nav_host_fragment_main) {
    private val bindingView by viewBinding(FragmentMainBinding::bind)

    override fun setupNavigation() {
        bindingView.navViewMain.setupWithNavController(navController)
    }
}