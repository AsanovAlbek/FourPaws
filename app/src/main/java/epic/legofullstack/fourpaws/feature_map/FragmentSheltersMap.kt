package epic.legofullstack.fourpaws.feature_map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.FragmentMapsBinding

class FragmentSheltersMap : Fragment(R.layout.fragment_maps) {

    private var _binding : FragmentMapsBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapsBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}