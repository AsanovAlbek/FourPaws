package epic.legofullstack.fourpaws.feature.facts.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.FragmentFactDetailBinding
import epic.legofullstack.fourpaws.feature.base.BaseFragment
import epic.legofullstack.fourpaws.feature.facts.presentation.dto.FactViewState

@AndroidEntryPoint
class FactDetailFragment : BaseFragment(R.layout.fragment_fact_detail) {
    private val bindingFact by viewBinding(FragmentFactDetailBinding::bind)
    private val viewModel: FactDetailViewModel by viewModels()
    private val args: FactDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner, ::handleState)
        viewModel.commands.observe(viewLifecycleOwner, ::handleCommand)
        viewModel.getFacts(args.factId)
    }

    private fun handleState(state: FactViewState) {
        refresh(state)
        when(state) {
            is FactViewState.DetailContent -> state.handle()
            is FactViewState.Error -> state.handle()
            else -> {}
        }
    }

    private fun refresh(state: FactViewState) {
        with(bindingFact) {
            loadingProgress.isVisible = state is FactViewState.Loading
            factDetailScrollView.isVisible = state is FactViewState.DetailContent
            factDetailErrorPanel.root.isVisible = state is FactViewState.Error
        }
    }

    private fun FactViewState.Error.handle() {
        with(bindingFact.factDetailErrorPanel) {
            errorIcon.setImageResource(errorModel.icon)
            errorText.setText(errorModel.title)
        }
    }

    private fun FactViewState.DetailContent.handle() {
        with(bindingFact) {
            factTitle.text = fact.title
            factText.text = fact.text
            Glide.with(this@FactDetailFragment)
                .load(fact.imgUrl)
                .error(R.drawable.ic_sad)
                .into(factImg)
        }
    }
}