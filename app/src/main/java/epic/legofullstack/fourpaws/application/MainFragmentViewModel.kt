package epic.legofullstack.fourpaws.application

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import epic.legofullstack.fourpaws.core.di.DispatchersModule
import epic.legofullstack.fourpaws.core.domain.model.Area
import epic.legofullstack.fourpaws.core.domain.usecase.PreferenceDataStoreUseCase
import epic.legofullstack.fourpaws.feature.base.BaseViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val dataStore: PreferenceDataStoreUseCase,
    @DispatchersModule.IoDispatcher private val io: CoroutineDispatcher
): BaseViewModel() {
    private val _userArea = MutableLiveData<Area>()
    val area: LiveData<Area> get() = _userArea

    init {
        viewModelScope.launch {
            dataStore.getUserArea()
                .flowOn(io)
                .collect { collectedArea ->
                    _userArea.value = collectedArea
                }
        }
    }
}