package epic.legofullstack.fourpaws.application

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.core.domain.model.Area
import epic.legofullstack.fourpaws.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.state.observe(this, ::handleStateCity)
    }

    private fun handleStateCity(area: Area) {
        setupNavigation(area)
    }

    private fun setupNavigation(userArea: Area) {
        val navHostFragment =
            supportFragmentManager
                .findFragmentById(R.id.nav_host_activity_main) as NavHostFragment
        navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)

        if (userArea.id == USER_AREA_DEFAULT) {
            navGraph.setStartDestination(R.id.nav_location_main_fragment)
        } else {
            navGraph.setStartDestination(R.id.nav_main_fragment)
        }
        navController.graph = navGraph
    }

    companion object {
        private const val TAG = "MainActivity-FP"
        private const val USER_AREA_DEFAULT = 0
    }
}