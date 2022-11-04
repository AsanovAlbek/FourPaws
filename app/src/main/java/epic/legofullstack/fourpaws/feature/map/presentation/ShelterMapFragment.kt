package epic.legofullstack.fourpaws.feature.map.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.FragmentShelterMapBinding
import epic.legofullstack.fourpaws.feature.base.BaseFragment
import epic.legofullstack.fourpaws.feature.map.presentation.dto.ShelterMapViewState

@AndroidEntryPoint
class ShelterMapFragment : BaseFragment(R.layout.fragment_shelter_map) {
    private val bindingSm by viewBinding(FragmentShelterMapBinding::bind)
    private val viewModel: ShelterMapViewModel by viewModels()
    private lateinit var adapter: GroupieAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = GroupieAdapter()
        bindingSm.recyclerShelterMap.adapter = adapter

        viewModel.uiState.observe(viewLifecycleOwner, ::handleViewState)
        viewModel.commands.observe(viewLifecycleOwner, ::handleCommand)
    }

    private fun handleViewState(state: ShelterMapViewState) {
        refresh(state)
        when (state) {
            is ShelterMapViewState.Error -> state.handle()
            is ShelterMapViewState.Content -> state.handle()
            is ShelterMapViewState.Loading -> {}
        }
    }

    private fun ShelterMapViewState.Content.handle() {
        adapter.update(shelters)
    }

    private fun ShelterMapViewState.Error.handle() {
        with(bindingSm.mapErrorPanel) {
            errorIcon.setImageResource(errorModel.icon)
            errorText.setText(errorModel.title)
        }
    }

    private fun refresh(state: ShelterMapViewState) {
        with(bindingSm) {
            shelterMapLayout.isVisible = state is ShelterMapViewState.Content
            loadingProgress.isVisible = state is ShelterMapViewState.Loading
            mapErrorPanel.root.isVisible = state is ShelterMapViewState.Error
        }
    }
}


