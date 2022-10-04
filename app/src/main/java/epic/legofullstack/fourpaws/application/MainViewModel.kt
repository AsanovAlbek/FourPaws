package epic.legofullstack.fourpaws.application

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import epic.legofullstack.fourpaws.core.di.DispatchersModule
import epic.legofullstack.fourpaws.core.domain.usecase.PreferenceDataStoreUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferenceGlobal: PreferenceDataStoreUseCase,
    @DispatchersModule.IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _userCity = MutableLiveData<String>()
    val userCity: LiveData<String> get() = _userCity

    init {
        viewModelScope.launch {
            preferenceGlobal.getUserCity()
                .flowOn(ioDispatcher)
                .collect {
                    _userCity.postValue(it)
                }
        }
    }
}