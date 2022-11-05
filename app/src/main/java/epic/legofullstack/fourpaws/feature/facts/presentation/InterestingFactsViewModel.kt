package epic.legofullstack.fourpaws.feature.facts.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import epic.legofullstack.fourpaws.core.di.DispatchersModule
import epic.legofullstack.fourpaws.feature.base.BaseViewModel
import epic.legofullstack.fourpaws.feature.base.OpenFragment
import epic.legofullstack.fourpaws.feature.facts.domain.model.FactPreview
import epic.legofullstack.fourpaws.feature.facts.domain.usecase.FactUseCase
import epic.legofullstack.fourpaws.feature.facts.presentation.dto.FactViewState
import epic.legofullstack.fourpaws.feature.facts.presentation.dto.ItemFact
import epic.legofullstack.fourpaws.feature.home.presentation.parseError
import epic.legofullstack.fourpaws.network.errorhandle.ResponseState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class InterestingFactsViewModel @Inject constructor(
    private val factUseCase: FactUseCase,
    @DispatchersModule.MainDispatcher
    private val mainDispatcher: CoroutineDispatcher
) : BaseViewModel() {
    private val factState =  MutableLiveData<FactViewState>(FactViewState.Loading)
    val uiState get() = factState

    init {
        viewModelScope.launch {
            factState.value = FactViewState.Loading
            when(val result = factUseCase.getFacts()) {
                is ResponseState.Success -> handleSuccess(result.data)
                is ResponseState.Error -> factState.value = FactViewState.Error(errorModel = result.isNetworkError.parseError())
            }
        }
    }

    private suspend fun handleSuccess(facts: List<FactPreview>) {
        withContext(mainDispatcher) {
            val items = facts.map {
                ItemFact(fact = it, onClick = { factItemClick(it.id) })
            }
            factState.value = FactViewState.Content(items)
        }
    }

    private fun factItemClick(factId: Int) {
        commands.value = OpenFragment(directions = InterestingFactsFragmentDirections.actionNavigationFactsToFactDetail(factId))
    }
}