package epic.legofullstack.fourpaws.application

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import epic.legofullstack.fourpaws.core.di.DispatchersModule
import epic.legofullstack.fourpaws.core.domain.model.Area
import epic.legofullstack.fourpaws.core.domain.usecase.PreferenceDataStoreUseCase
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

@HiltViewModel
class MainViewModel @Inject constructor(
    private val areaStorage: PreferenceDataStoreUseCase,
    @DispatchersModule.IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _userArea = MutableLiveData<Area>()
    val state: LiveData<Area> get() = _userArea

    // todo обработать ошибку
    init {
        viewModelScope.launch {
            areaStorage.getUserArea()
                .flowOn(ioDispatcher)
                .collect {
                    _userArea.postValue(it)
                }
        }
    }
}