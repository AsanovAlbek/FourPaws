package epic.legofullstack.fourpaws.application

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import epic.legofullstack.fourpaws.application.dto.MainViewState
import epic.legofullstack.fourpaws.core.domain.usecase.PreferenceDataStoreUseCase
import epic.legofullstack.fourpaws.feature.home.presentation.parseError
import epic.legofullstack.fourpaws.network.errorhandle.handleResult
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val areaStorage: PreferenceDataStoreUseCase
) : ViewModel() {
    private val _userArea = MutableLiveData<MainViewState>()
    val state: LiveData<MainViewState> get() = _userArea

    init {
        viewModelScope.launch {
            areaStorage.getUserArea().handleResult (
                { _userArea.value = MainViewState.Error(it.parseError()) },
                {  _userArea.value = MainViewState.Content(it) }
            )
        }
    }
}