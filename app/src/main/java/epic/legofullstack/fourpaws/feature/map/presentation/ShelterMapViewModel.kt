package epic.legofullstack.fourpaws.feature.map.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.core.di.DispatchersModule
import epic.legofullstack.fourpaws.core.domain.usecase.PreferenceDataStoreUseCase
import epic.legofullstack.fourpaws.core.presentation.ResourcesProvider
import epic.legofullstack.fourpaws.feature.base.BaseViewModel
import epic.legofullstack.fourpaws.feature.base.CopyText
import epic.legofullstack.fourpaws.feature.base.ShowSnackbar
import epic.legofullstack.fourpaws.feature.base.StartActivityForMap
import epic.legofullstack.fourpaws.feature.home.presentation.parseError
import epic.legofullstack.fourpaws.feature.map.domain.model.Shelter
import epic.legofullstack.fourpaws.feature.map.domain.usecase.ShelterUseCase
import epic.legofullstack.fourpaws.feature.map.presentation.dto.ShelterMapViewState
import epic.legofullstack.fourpaws.feature.map.presentation.item.ShelterMapItem
import epic.legofullstack.fourpaws.network.errorhandle.ResponseState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ShelterMapViewModel @Inject constructor(
    private val shelterUseCase: ShelterUseCase,
    private val areaStorage: PreferenceDataStoreUseCase,
    private val resourcesProvider: ResourcesProvider,
    @DispatchersModule.IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    @DispatchersModule.MainDispatcher
    private val mainDispatcher: CoroutineDispatcher,
) : BaseViewModel() {

    private val content = MutableLiveData<ShelterMapViewState>(ShelterMapViewState.Loading)
    val uiState: LiveData<ShelterMapViewState> get() = content

    init {
        viewModelScope.launch {
            content.value = ShelterMapViewState.Loading
            areaStorage.getUserArea()
                .flowOn(ioDispatcher)
                .collect {
                    when (val res = shelterUseCase.getShelterByAreaId(it.id)) {
                        is ResponseState.Success -> handleSuccess(res.data)
                        is ResponseState.Error -> handleError(res.isNetworkError)
                    }
                }
        }
    }

    private suspend fun handleError(isNetworkError: Boolean) =
        withContext(mainDispatcher) {
            content.value = ShelterMapViewState.Error(errorModel = isNetworkError.parseError())
        }

    private suspend fun handleSuccess(shelters: List<Shelter>) {
        withContext(mainDispatcher) {
            if (shelters.isEmpty()) {
                content.value = ShelterMapViewState.Content(emptyList())
                commands.value = ShowSnackbar(
                    text = resourcesProvider.getString(R.string.no_shelters_in_region),
                )
            } else {
                val items = shelters.map {
                    ShelterMapItem(
                        shelter = it,
                        openMap = { openMap(it.longitude, it.latitude) },
                        copyAddress = { copyAddress(it.address)}
                    )
                }
                content.value = ShelterMapViewState.Content(items)
            }
        }
    }

    private fun openMap(longitude: Float, latitude: Float) {
        commands.value = StartActivityForMap(longitude, latitude)
    }

    private fun copyAddress(address: String){
        commands.value = CopyText(resourcesProvider.getString(R.string.label_shelter_address), address)
    }
}