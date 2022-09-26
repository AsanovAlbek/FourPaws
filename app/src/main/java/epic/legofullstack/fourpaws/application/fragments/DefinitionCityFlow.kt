package epic.legofullstack.fourpaws.application.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.FlowFragmentDeterminationLocationBinding

class DefinitionCityFlow : Fragment(R.layout.flow_fragment_determination_location) {
    private lateinit var binding: FlowFragmentDeterminationLocationBinding
    private lateinit var navController : NavController


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FlowFragmentDeterminationLocationBinding.inflate(layoutInflater)

        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.nav_host_fragment_definition_city) as NavHostFragment
        navController = navHostFragment.navController
    }
}