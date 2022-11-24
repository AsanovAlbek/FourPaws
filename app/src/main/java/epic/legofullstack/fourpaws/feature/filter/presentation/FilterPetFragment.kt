package epic.legofullstack.fourpaws.feature.filter.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.core.domain.model.Area
import epic.legofullstack.fourpaws.databinding.FragmentFilterPetBinding
import epic.legofullstack.fourpaws.feature.base.BaseFragment
import epic.legofullstack.fourpaws.feature.filter.presentation.adapter.AreaAdapter
import epic.legofullstack.fourpaws.feature.filter.presentation.state.UiState
import epic.legofullstack.fourpaws.network.firebase.data.model.Age
import epic.legofullstack.fourpaws.network.firebase.data.model.PetType

@AndroidEntryPoint
class FilterPetFragment : BaseFragment(R.layout.fragment_filter_pet) {
    private val bindingFilter by viewBinding(FragmentFilterPetBinding::bind)
    private val filterViewModel: FilterViewModel by viewModels()
    private lateinit var areaAdapter: AreaAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        listeners()
    }

    private fun observe() {
        filterViewModel.apply {
            filterState.observe(viewLifecycleOwner, ::handleState)
            commands.observe(viewLifecycleOwner, ::handleCommand)
        }
    }

    private fun handleState(uiState: UiState) {
        refreshUi(uiState)
        when (uiState) {
            is UiState.Content -> uiState.handleContent()
            is UiState.Loading -> Log.i(TAG, "Loading")
        }
    }

    private fun refreshUi(uiState: UiState) {
        bindingFilter.apply {
            content.isVisible = uiState is UiState.Content
            filterProgress.isVisible = uiState is UiState.Loading
        }
    }

    private fun UiState.Content.handleContent() {
        areaAdapter = AreaAdapter(
            context = bindingFilter.root.context,
            layoutResourceId = R.layout.auto_complete_text_view_item,
            areas = areas
        )
        bindingFilter.apply {
            searchAreaField.apply {
                setAdapter(areaAdapter)
                setText(userArea, false)
            }

            // установить значения фильтра
            filter.apply {
                if (petType != null) {
                    petTypeToggles.check(if (petType == PetType.DOG) dogToggle.id else catToggle.id)
                }
                if (gender != null && gender.isNotEmpty()) {
                    maleToggles.check(if (gender.equals(getString(R.string.male))) maleToggle.id else femaleToggle.id)
                }
                if (age != null && age != Age.UNKNOWN) {
                    when (age) {
                        Age.BABY -> ageChipGroup.check(babyChip.id)
                        Age.ADULT -> ageChipGroup.check(adultChip.id)
                        Age.YOUNG -> ageChipGroup.check(youngChip.id)
                        Age.ELDERLY -> ageChipGroup.check(elderlyChip.id)
                        Age.UNKNOWN -> {}
                    }
                }
                if (characteristics != null && characteristics.isNotEmpty()) {
                    characteristics.forEach {
                        when (it) {
                            getString(R.string.sterilized) -> othersChipGroup.check(sterilizedChip.id)
                            getString(R.string.accustomed_tray) -> othersChipGroup.check(accustomedTrayChip.id)
                            getString(R.string.vaccinated) -> othersChipGroup.check(vaccinatedChip.id)
                            getString(R.string.friendly) -> othersChipGroup.check(friendlyChip.id)
                        }
                    }
                }
            }
        }
    }

    private fun listeners() {
        bindingFilter.apply {
            applyButton.setOnClickListener { filterViewModel.applyFilter() }
            petTypeToggles.setOnCheckedStateChangeListener(::chipsChangeListener)
            maleToggles.setOnCheckedStateChangeListener(::chipsChangeListener)
            ageChipGroup.setOnCheckedStateChangeListener(::chipsChangeListener)
            othersChipGroup.setOnCheckedStateChangeListener(::chipsChangeListener)
            searchAreaField.setOnItemClickListener { adapterView, _, position, _ ->
                val currentArea = adapterView.adapter.getItem(position) as Area
                filterViewModel.setArea(currentArea)
                searchAreaField.setText(currentArea.title)
            }
        }
    }

    private fun chipsChangeListener(chipGroup: ChipGroup, checkedChipsIds: MutableList<Int>) {
        when (chipGroup) {
            bindingFilter.othersChipGroup -> filterViewModel.filterByCharacteristics(
                chipGroup,
                checkedChipsIds
            )
            bindingFilter.ageChipGroup -> filterViewModel.updateFilter(FilterViewModel.AGE_FIELD, chipGroup)
            bindingFilter.petTypeToggles -> filterViewModel.updateFilter(FilterViewModel.PET_TYPE_FIELD, chipGroup)
            bindingFilter.maleToggles -> filterViewModel.updateFilter(FilterViewModel.GENDER_FIELD, chipGroup)
        }
    }

    companion object {
        private const val TAG = "FilterPetFragment-FP"
    }
}