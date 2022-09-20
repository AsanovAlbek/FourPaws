package epic.legofullstack.fourpaws.feature.facts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.FragmentInterestingFactsBinding

class FragmentInterestingFacts : Fragment(R.layout.fragment_interesting_facts) {

    private var _binding : FragmentInterestingFactsBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInterestingFactsBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}