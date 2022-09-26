package epic.legofullstack.fourpaws.application.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.FlowFragmentMainBinding

class MainFlow : Fragment(R.layout.flow_fragment_main) {
    private lateinit var binding: FlowFragmentMainBinding
    private lateinit var navController : NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FlowFragmentMainBinding.inflate(layoutInflater)
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHostFragment.navController

        setupNavigation()
    }

    private fun setupNavigation() {
        val navView: BottomNavigationView = binding.navViewMain
        navView.setupWithNavController(navController)
    }

}