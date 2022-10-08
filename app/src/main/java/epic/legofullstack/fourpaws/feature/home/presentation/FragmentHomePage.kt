package epic.legofullstack.fourpaws.feature.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.FragmentHomePageBinding
import epic.legofullstack.fourpaws.feature.home.domain.model.Pet
import epic.legofullstack.fourpaws.feature.home.presentation.adapter.HomePagePetListAdapter
import epic.legofullstack.fourpaws.feature.home.presentation.dto.UiState

@AndroidEntryPoint
class FragmentHomePage : Fragment(R.layout.fragment_home_page) {

    private var _binding : FragmentHomePageBinding? = null
    private val binding get() = _binding
    private val homePageViewModel : HomePageViewModel by viewModels()
    private lateinit var petsHomePageAdapter : HomePagePetListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomePageBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observe()
        homePageViewModel.executeWhenCreated()
    }

    private fun observe() {
        homePageViewModel.state.observe(viewLifecycleOwner, ::handleState)
    }

    private fun handleState(uiState: UiState) {
        refresh(uiState)
        when(uiState) {
            is UiState.Content -> uiState.contentHandle()
            is UiState.Error -> uiState.errorHandle()
            else -> {}
        }
    }

    private fun UiState.Content.contentHandle() {
        setPetsList(pets)
        petsHomePageAdapter.refreshPets(pets)
    }

    private fun UiState.Error.errorHandle() {
        binding?.errorPanel?.let {
            with(it) {
                errorIcon.setImageResource(errorModel.icon)
                errorText.setText(errorModel.title)
            }
        }
    }

    private fun refresh(uiState: UiState) {
            binding?.let {
                with(it) {
                    petsList.isVisible = uiState is UiState.Content
                    errorPanel.root.isVisible = uiState is UiState.Error
                    loadingProgress.isVisible = uiState is UiState.Loading
                }
            }
    }

    private fun setPetsList(pets : List<Pet>) {
        petsHomePageAdapter = HomePagePetListAdapter(pets.toMutableList())
            binding?.petsList?.let {
                with(it) {
                    adapter = petsHomePageAdapter
                    layoutManager = LinearLayoutManager(requireContext())
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}