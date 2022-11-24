package epic.legofullstack.fourpaws.feature.home.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.FragmentHomePageBinding
import epic.legofullstack.fourpaws.feature.base.BaseFragment
import epic.legofullstack.fourpaws.feature.home.domain.model.Pet
import epic.legofullstack.fourpaws.feature.home.presentation.adapter.HomePagePetListAdapter
import epic.legofullstack.fourpaws.feature.home.presentation.dto.UiState

@AndroidEntryPoint
class FragmentHomePage : BaseFragment(R.layout.fragment_home_page) {

    private val homeBinding by viewBinding(FragmentHomePageBinding::bind)
    private val homePageViewModel : HomePageViewModel by viewModels()
    private lateinit var petsHomePageAdapter : HomePagePetListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observe()
        homePageViewModel.executeWhenCreated()
    }

    private fun observe() {
        homePageViewModel.state.observe(viewLifecycleOwner, ::handleState)
        homePageViewModel.commands.observe(viewLifecycleOwner, ::handleCommand)
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
        if (pets.isEmpty()) {
            homeBinding.textViewEmptyList.isVisible = true
        } else {
            setPetsList(pets)
            petsHomePageAdapter.refreshPets(pets)
        }
    }

    private fun UiState.Error.errorHandle() {
        homeBinding.errorPanel.apply {
            errorIcon.setImageResource(errorModel.icon)
            errorText.setText(errorModel.title)
        }
    }

    private fun refresh(uiState: UiState) {
        homeBinding.apply {
            petsList.isVisible = uiState is UiState.Content
            errorPanel.root.isVisible = uiState is UiState.Error
            loadingProgress.isVisible = uiState is UiState.Loading
        }
    }

    private fun setPetsList(pets : List<Pet>) {
        petsHomePageAdapter =
            HomePagePetListAdapter(pets = pets.toMutableList(), itemClick =  ::openDetails)
        homeBinding.petsList.apply {
            adapter = petsHomePageAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun openDetails(id: Int) {
        homePageViewModel.clickToPet(petId = id)
    }
}