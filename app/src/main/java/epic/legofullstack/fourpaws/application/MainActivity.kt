package epic.legofullstack.fourpaws.application

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import epic.legofullstack.fourpaws.R
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

        viewModel.userCity.observe(this, ::handleStateCity)
    }

    private fun handleStateCity(city: String) {
        setupNavigation(city)
    }

    private fun setupNavigation(userCity: String?) {
        val navHostFragment =
            supportFragmentManager
                .findFragmentById(R.id.nav_host_activity_main) as NavHostFragment
        navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)

        if (userCity == null) {
            navGraph.setStartDestination(R.id.nav_location_main_fragment)
        } else {
            navGraph.setStartDestination(R.id.nav_main_fragment)
        }
        navController.graph = navGraph
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}