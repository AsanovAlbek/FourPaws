package epic.legofullstack.fourpaws.feature.location.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.FragmentLocationBinding
import epic.legofullstack.fourpaws.extensions.activityNavController
import epic.legofullstack.fourpaws.extensions.navigateSafely

@AndroidEntryPoint
class LocationFragment : Fragment(R.layout.fragment_location) {
    private lateinit var bindingL: FragmentLocationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingL = FragmentLocationBinding.inflate(inflater, container, false)

        bindingL.bChooseCity.setOnClickListener {
            activityNavController().navigateSafely(R.id.action_global_mainFlowFragment)
        }

        return bindingL.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingL.bChooseCity.isEnabled = true
    }

    companion object {
        private const val TAG = "LocationFragment"
    }
}