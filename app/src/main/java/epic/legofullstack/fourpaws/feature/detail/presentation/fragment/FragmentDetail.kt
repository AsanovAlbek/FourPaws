package epic.legofullstack.fourpaws.feature.detail.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.FragmentPetDetailBinding
import epic.legofullstack.fourpaws.extensions.activityNavController
import epic.legofullstack.fourpaws.extensions.fragmentNavController
import epic.legofullstack.fourpaws.feature.base.BaseFragment
import epic.legofullstack.fourpaws.feature.base.NavigateUp
import epic.legofullstack.fourpaws.feature.base.ShowDialog
import epic.legofullstack.fourpaws.feature.detail.domain.model.Pet
import epic.legofullstack.fourpaws.feature.detail.presentation.dto.UiState
import epic.legofullstack.fourpaws.feature.detail.presentation.viewmodel.DetailsViewModel

class FragmentDetail : BaseFragment(R.layout.fragment_pet_detail) {
    private val detailBinding by viewBinding(FragmentPetDetailBinding::bind)
    private val viewModel: DetailsViewModel by viewModels()
    private val args: FragmentDetailArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observe()
        viewModel.findPet(id = args.clickedPetId, navController =  activityNavController())
    }

    private fun observe() {
        viewModel.state.observe(viewLifecycleOwner, ::handleState)
        viewModel.commands.observe(viewLifecycleOwner, ::handleCommand)
    }

    private fun clickListeners(pet: Pet) {
        changeStarButtonTint(pet)
        detailBinding.apply {
            callButton.setOnClickListener {
                viewModel.callDeepLink(pet)
            }

            sendEmailButton.setOnClickListener {
                viewModel.sendEmailDeepLink(pet)
            }

            sharedPostButton.setOnClickListener {
                viewModel.shareDeepLink(pet)
            }

            starButton.setOnClickListener {
                changeFavorite(pet = pet)
                changeStarButtonTint(pet = pet)
            }
        }
    }

    private fun changeFavorite(pet: Pet) {
        if (!pet.isFavorite) {
            viewModel.addPetToFavorite(pet = pet)
        } else {
            viewModel.removePetToFavorite(pet = pet)
        }
    }

    private fun changeStarButtonTint(pet: Pet) {
        val alreadyIsFavoriteColor = requireActivity().resources.getColor(R.color.star_button, null)
        val notFavoriteColor = requireActivity().resources.getColor(R.color.black, null)
        detailBinding.starButton.icon.apply {
            if (pet.isFavorite) {
                setTint(alreadyIsFavoriteColor)
            } else {
                setTint(notFavoriteColor)
            }
        }
    }

    private fun handleState(uiState: UiState) {
        detailBinding.detailContent.isVisible = uiState is UiState.Content
        when(uiState) {
            is UiState.Content -> uiState.handleContent()
            is UiState.Error -> uiState.handleError()
            else -> {}
        }
    }

    private fun UiState.Error.handleError() {
        baseViewModel.commands.value = ShowDialog(
            title = requireContext().resources.getString(R.string.error),
            message = requireContext().resources.getString(R.string.cant_find_pet_error),
            positiveButtonText = requireContext().resources.getString(R.string.back),
            callbackPositiveButton = { viewModel.commands.value = NavigateUp(fragmentNavController()) },
            isCancelable = false
        )
    }

    private fun UiState.Content.handleContent() {
        detailBinding.apply {
            with(pet) {
                petNameAndAgeText.text = name
                addressText.text = shelter.address
                shelterText.text = shelter.name
            }
        }
        clickListeners(pet = pet)
    }
}
