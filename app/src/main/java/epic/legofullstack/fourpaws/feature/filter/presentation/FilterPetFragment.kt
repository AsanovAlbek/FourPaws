package epic.legofullstack.fourpaws.feature.filter.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.FragmentFilterPetBinding
import epic.legofullstack.fourpaws.feature.base.BaseFragment
import epic.legofullstack.fourpaws.feature.filter.domain.model.PetFilterModel
import epic.legofullstack.fourpaws.feature.filter.presentation.adapter.AreaAdapter
import epic.legofullstack.fourpaws.feature.filter.presentation.state.UiState

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
            areaListState.observe(viewLifecycleOwner, ::handleState)
            filter.observe(viewLifecycleOwner, ::handleFilter)
            commands.observe(viewLifecycleOwner, ::handleCommand)
        }
    }

    private fun handleFilter(petFilterModel: PetFilterModel) {
        // todo(Возможно не придётся за ним наблюдать отсюда)
    }

    private fun handleState(uiState: UiState) {
        refreshUi(uiState)
        when(uiState) {
            is UiState.Content -> uiState.handleContent()
            is UiState.Error -> uiState.handleError()
            is UiState.Loading -> Log.i("filter", "Loading")
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
            areas = data
        )
        bindingFilter.searchAreaField.setAdapter(areaAdapter)
    }

    private fun UiState.Error.handleError() {
        // todo(Позже сам прикручу обработку ошибки)
    }

    private fun listeners() {
        bindingFilter.apply {
            applyButton.setOnClickListener { filterViewModel.applyFilter() }
            petTypeToggles.addOnButtonCheckedListener(::buttonsCheckedListener)
            maleToggles.addOnButtonCheckedListener(::buttonsCheckedListener)
            ageChipGroup.setOnCheckedStateChangeListener(::chipsChangeListener)
            othersChipGroup.setOnCheckedStateChangeListener(::chipsChangeListener)
            bindingFilter.searchAreaField.setOnItemClickListener { adapterView, _, position, _ ->
                // todo(Дописать выбор Area и сразу сохранять)
                //filterViewModel.saveArea()
            }

        }
    }

    private fun chipsChangeListener(chipGroup: ChipGroup, checkedChipsIds: MutableList<Int>) {
        if (chipGroup == bindingFilter.othersChipGroup) {
            filterViewModel.filterByCharacteristics(chipGroup, checkedChipsIds)
        } else if (chipGroup == bindingFilter.ageChipGroup) {
            filterViewModel.filterByAge(chipGroup)
        }
    }

    private fun buttonsCheckedListener(
        group: MaterialButtonToggleGroup,
        toggleButtonId: Int,
        isChecked: Boolean
    ) {
        if (isChecked) {
            when(toggleButtonId) {
                R.id.catToggle -> filterViewModel.filterByPetType(resources.getString(R.string.cat))
                R.id.dogToggle -> filterViewModel.filterByPetType(resources.getString(R.string.dog))
                R.id.maleToggle -> filterViewModel.filterByGender(resources.getString(R.string.male))
                R.id.femaleToggle -> filterViewModel.filterByGender(resources.getString(R.string.female))
            }
        }
    }

}