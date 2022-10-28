package epic.legofullstack.fourpaws.feature.facts.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.FragmentInterestingFactsBinding
import epic.legofullstack.fourpaws.feature.base.BaseFragment
import epic.legofullstack.fourpaws.feature.facts.presentation.dto.FactViewState

@AndroidEntryPoint
class InterestingFactsFragment : BaseFragment(R.layout.fragment_interesting_facts) {
    private val bindingFact by viewBinding(FragmentInterestingFactsBinding::bind)
    private val viewModel: InterestingFactsViewModel by viewModels()
    private lateinit var adapter: GroupieAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = GroupieAdapter()
        bindingFact.factsRecyclerView.adapter = adapter
        viewModel.commands.observe(viewLifecycleOwner, ::handleCommand)
        viewModel.uiState.observe(viewLifecycleOwner, ::handleState)
    }

    private fun handleState(state: FactViewState) {
        refresh(state)
        when (state) {
            is FactViewState.Content -> state.handle()
            is FactViewState.Error -> state.handle()
            else -> { }
        }
    }

    private fun FactViewState.Error.handle() {
        with(bindingFact.factErrorPanel) {
            errorIcon.setImageResource(errorModel.icon)
            errorText.setText(errorModel.title)
        }
    }

    private fun FactViewState.Content.handle() {
        adapter.update(facts)
    }

    private fun refresh(state: FactViewState) {
        with(bindingFact) {
            loadingProgress.isVisible = state is FactViewState.Loading
            factsRecyclerView.isVisible = state is FactViewState.Content
            factErrorPanel.root.isVisible = state is FactViewState.Error
        }
    }
}