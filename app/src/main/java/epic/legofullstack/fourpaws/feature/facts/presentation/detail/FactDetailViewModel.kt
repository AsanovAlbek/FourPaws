package epic.legofullstack.fourpaws.feature.facts.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.core.presentation.ResourcesProvider
import epic.legofullstack.fourpaws.feature.base.BaseViewModel
import epic.legofullstack.fourpaws.feature.base.NavigateUp
import epic.legofullstack.fourpaws.feature.base.ShowDialog
import epic.legofullstack.fourpaws.feature.facts.domain.model.Fact
import epic.legofullstack.fourpaws.feature.facts.domain.usecase.FactUseCase
import epic.legofullstack.fourpaws.feature.facts.presentation.dto.FactViewState
import epic.legofullstack.fourpaws.feature.home.presentation.parseError
import epic.legofullstack.fourpaws.network.errorhandle.ResponseState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FactDetailViewModel @Inject constructor(
    private val factUseCase: FactUseCase,
    private val resourcesProvider: ResourcesProvider
) : BaseViewModel() {
    private val _state = MutableLiveData<FactViewState>(FactViewState.Loading)
    val state: LiveData<FactViewState> get() = _state

    fun getFacts(factId: Int) {
        viewModelScope.launch {
            _state.value = FactViewState.Loading
            when (val result = factUseCase.getFactById(factId)) {
                is ResponseState.Success -> handleSuccess(result.data)
                is ResponseState.Error -> _state.value =
                    FactViewState.Error(errorModel = result.isNetworkError.parseError())
            }
        }
    }

    private fun handleSuccess(fact: Fact) {
            _state.value = FactViewState.DetailContent(fact)
            if (fact.id == Fact.DEFAULT_FACT_ID) {
                commands.value = ShowDialog(
                    title = resourcesProvider.getString(R.string.error),
                    message = resourcesProvider.getString(R.string.unknown_error),
                    positiveButtonText = resourcesProvider.getString(R.string.ok),
                    callbackPositiveButton = { commands.value = NavigateUp() }
                )
            }
    }
}
