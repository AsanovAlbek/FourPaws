package epic.legofullstack.fourpaws.feature.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.FragmentHomePageBinding
import epic.legofullstack.fourpaws.feature.home.domain.model.Pet

@AndroidEntryPoint
class FragmentHomePage : Fragment(R.layout.fragment_home_page) {

    private var _binding : FragmentHomePageBinding? = null
    private val binding get() = _binding
    private val homePageViewModel : HomePageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomePageBinding.inflate(layoutInflater)
        observe()
        homePageViewModel.getAllPets()
        return binding!!.root
    }

    private fun observe() {
        homePageViewModel.allPets.observe(viewLifecycleOwner, ::setPets)
    }

    private fun setPets(pets : List<Pet>) {
        pets.forEach { println(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}