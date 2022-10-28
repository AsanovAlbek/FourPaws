package epic.legofullstack.fourpaws.feature.facts.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import epic.legofullstack.fourpaws.feature.facts.domain.usecase.FactUseCase
import epic.legofullstack.fourpaws.feature.facts.presentation.dto.FactViewState
import epic.legofullstack.fourpaws.feature.home.presentation.parseError
import epic.legofullstack.fourpaws.network.errorhandle.ResponseState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FactDetailViewModel @Inject constructor(
    private val factUseCase: FactUseCase
) : ViewModel() {
    private val _state = MutableLiveData<FactViewState>(FactViewState.Loading)
    val state: LiveData<FactViewState> get() = _state

    fun getFacts(factId: Int) {
        viewModelScope.launch {
            _state.value = FactViewState.Loading
            when (val result = factUseCase.getFactById(factId)) {
                is ResponseState.Success -> _state.value = FactViewState.DetailContent(result.data)
                is ResponseState.Error -> _state.value =
                    FactViewState.Error(errorModel = result.isNetworkError.parseError())
            }
        }
    }
}
