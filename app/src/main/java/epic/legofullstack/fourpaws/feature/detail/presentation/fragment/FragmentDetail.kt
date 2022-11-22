package epic.legofullstack.fourpaws.feature.detail.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.FragmentPetDetailBinding
import epic.legofullstack.fourpaws.extensions.fragmentNavController
import epic.legofullstack.fourpaws.feature.base.BaseFragment
import epic.legofullstack.fourpaws.feature.base.NavigateUp
import epic.legofullstack.fourpaws.feature.base.SharePet
import epic.legofullstack.fourpaws.feature.base.ShowDialog
import epic.legofullstack.fourpaws.feature.base.StartEmail
import epic.legofullstack.fourpaws.feature.base.StartTelephone
import epic.legofullstack.fourpaws.feature.detail.domain.model.PetDetail
import epic.legofullstack.fourpaws.feature.detail.presentation.adapter.ImagesSlider
import epic.legofullstack.fourpaws.feature.detail.presentation.dto.UiState
import epic.legofullstack.fourpaws.feature.detail.presentation.mapAge
import epic.legofullstack.fourpaws.feature.detail.presentation.mapCharacteristics
import epic.legofullstack.fourpaws.feature.detail.presentation.mapGender
import epic.legofullstack.fourpaws.feature.detail.presentation.viewmodel.DetailsViewModel

class FragmentDetail : BaseFragment(R.layout.fragment_pet_detail) {
    private val detailBinding by viewBinding(FragmentPetDetailBinding::bind)
    private val viewModel: DetailsViewModel by viewModels()
    private val args: FragmentDetailArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observe()
        viewModel.findPet(id = args.clickedPetId, navController =  fragmentNavController())
    }

    private fun observe() {
        viewModel.state.observe(viewLifecycleOwner, ::handleState)
        viewModel.commands.observe(viewLifecycleOwner, ::handleCommand)
        viewModel.isFavorite.observe(viewLifecycleOwner, ::changeStarButton)
    }

    private fun changeStarButton(isFavorite: Boolean) {
        val alreadyIsFavoriteColor = resources.getColor(R.color.star_button, null)
        val notFavoriteColor = resources.getColor(R.color.black, null)

        detailBinding.starButton.icon.setTint(
            if (isFavorite) notFavoriteColor else alreadyIsFavoriteColor
        )
    }

    private fun clickListeners(pet: PetDetail) {
        detailBinding.apply {
            callButton.setOnClickListener {
                viewModel.commands.value = StartTelephone(shelterPhoneNumber = pet.shelter.phone)
            }

            sendEmailButton.setOnClickListener {
                viewModel.commands.value = StartEmail(
                    petName = pet.name,
                    shelterEmail = pet.shelter.email
                )
            }

            sharedPostButton.setOnClickListener {
                viewModel.commands.value = SharePet(
                    petId = pet.id,
                   uriText = getString(R.string.url_pet_detail,pet.id)
                )
            }

            starButton.setOnClickListener {
                viewModel.changeFavorite(pet)
            }
        }
    }

    private fun refreshUi(state: UiState) {
        // TODO: утановить loading  по центру
        detailBinding.apply {
            detailContent.isVisible = state is UiState.Content
            loadingProgress.isVisible = state is UiState.Loading
        }
    }

    private fun handleState(uiState: UiState) {
        refreshUi(uiState)
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
        viewModel.changeFavorite(pet)
        detailBinding.apply {
            with(pet) {
                petNameAndAgeText.text = "$name, ${age.mapAge(requireContext())}"
                address.text = shelter.address
                shelterName.text = shelter.name
                summary.text = description
                maleText.text = gender.mapGender(requireContext())
                breedText.text = breed.name
                colorText.text = color
                viewModel.addChips(
                    chipGroup = secondaryParams,
                    listOfChipsText = pet.characteristics.mapCharacteristics(requireContext())
                )
                val imagesAdapter = ImagesSlider(images = imgs)
                petPhotosViewPager.adapter = imagesAdapter
            }
        }
        clickListeners(pet = pet)
    }
}
