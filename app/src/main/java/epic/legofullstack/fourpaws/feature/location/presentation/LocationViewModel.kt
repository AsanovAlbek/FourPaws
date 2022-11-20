package epic.legofullstack.fourpaws.feature.location.presentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.android.gms.tasks.CancellationTokenSource
import com.gun0912.tedpermission.coroutine.TedPermission
import dagger.hilt.android.lifecycle.HiltViewModel
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.core.di.DispatchersModule
import epic.legofullstack.fourpaws.core.domain.model.Area
import epic.legofullstack.fourpaws.core.domain.usecase.PreferenceDataStoreUseCase
import epic.legofullstack.fourpaws.core.presentation.ResourcesProvider
import epic.legofullstack.fourpaws.core.presentation.model.ErrorModel
import epic.legofullstack.fourpaws.feature.base.BaseViewModel
import epic.legofullstack.fourpaws.feature.base.OpenFragment
import epic.legofullstack.fourpaws.feature.base.ShowDialog
import epic.legofullstack.fourpaws.feature.home.presentation.parseError
import epic.legofullstack.fourpaws.feature.location.domain.usecase.AreaUseCase
import epic.legofullstack.fourpaws.feature.location.domain.usecase.UserLocationUseCase
import epic.legofullstack.fourpaws.feature.location.presentation.dto.LocationViewState
import epic.legofullstack.fourpaws.network.errorhandle.ResponseState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val userLocationUseCase: UserLocationUseCase,
    private val cancellationTokenSource: CancellationTokenSource,
    private val areaUseCase: AreaUseCase,
    private val preferenceDataStore: PreferenceDataStoreUseCase,

    @DispatchersModule.IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,

    @DispatchersModule.MainDispatcher
    private val mainDispatcher: CoroutineDispatcher,
    private val resourcesProvider: ResourcesProvider
) : BaseViewModel() {

    private val content = MutableLiveData<LocationViewState>(LocationViewState.Loading)
    val viewState: LiveData<LocationViewState> get() = content

    private var currentContent = LocationViewState.Content()
    private lateinit var chosenArea: Area

    init {
        viewModelScope.launch {
            content.value = LocationViewState.Loading
            withContext(ioDispatcher) {
                when (val result = areaUseCase.getAreas()) {
                    is ResponseState.Success -> areasHandleSuccess(result.data)
                    is ResponseState.Error -> handleError(result.isNetworkError)
                }
            }
        }
    }

    private suspend fun handleError(isNetworkError: Boolean) =
        withContext(mainDispatcher) {
            content.value = LocationViewState.Error(errorModel = isNetworkError.parseError())
        }


    private suspend fun areasHandleSuccess(areas: List<Area>) =
        withContext(mainDispatcher) {
            if(areas.isEmpty()) {
                content.value = LocationViewState.Error(
                    errorModel = ErrorModel(
                        R.string.error_loading_areas,
                        R.drawable.ic_sad
                    )
                )
            } else {
                currentContent = currentContent.copy(areas = areas)
                content.value = currentContent
            }
        }

    @RequiresPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
    fun initUserArea() {
        viewModelScope.launch {
            content.value = LocationViewState.Loading
            withContext(ioDispatcher) {
                when (val result = userLocationUseCase.getUserArea()) {
                    is ResponseState.Success -> userAreaHandleSuccess(result.data)
                    is ResponseState.Error -> handleError(result.isNetworkError)
                }
            }
        }
    }

    private suspend fun userAreaHandleSuccess(userArea: String?) {
        withContext(mainDispatcher) {
            if (userArea != null) {
                Log.d(TAG, "user area = $userArea")
                val existenceArea = checkingExistenceArea(userArea)
                if (existenceArea != null) {
                    chosenArea = existenceArea
                    currentContent = currentContent.copy(userArea = userArea, isIdentifyArea = true)
                    content.value = currentContent
                } else {
                    currentContent = currentContent.copy(isIdentifyArea = true)
                    content.value = currentContent
                }
            } else {
                currentContent = currentContent.copy(isIdentifyArea = true)
                content.value = currentContent
            }
        }
    }

    fun saveUserArea(navController: NavController) {
        viewModelScope.launch {
            withContext(ioDispatcher) {
                when (val result = preferenceDataStore.saveUserArea(chosenArea)) {
                    is ResponseState.Success -> handleSaveArea(navController)
                    is ResponseState.Error -> handleError(result.isNetworkError)
                }
            }
        }
    }

    private suspend fun showErrorDialog(text: String) {
        withContext(mainDispatcher) {
            commands.value = ShowDialog(
                resourcesProvider.getString(R.string.error),
                text,
                resourcesProvider.getString(R.string.ok)
            )
        }
    }

    private suspend fun handleSaveArea(navController: NavController) {
        withContext(mainDispatcher) {
            commands.value =
                OpenFragment(
                    navController = navController,
                    actionId = R.id.action_global_main_fragment
                )
        }
    }

    @SuppressLint("MissingPermission")
    fun getPermission(vararg permissions: String) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                val permissionResult = TedPermission.create()
                    .setRationaleTitle(R.string.permission_title)
                    .setRationaleMessage(R.string.permission_gps_text)
                    .setDeniedTitle(R.string.permission_denied_title)
                    .setDeniedMessage(R.string.permission_denied_explanation)
                    .setGotoSettingButtonText(R.string.settings)
                    .setDeniedCloseButtonText(R.string.cancel)
                    .setPermissions(*permissions)
                    .setRationaleConfirmText(R.string.ok)
                    .check()

                if (permissionResult.isGranted) {
                    initUserArea()
                } else {
                    showErrorDialog(resourcesProvider.getString(R.string.permission_error_text))
                    currentContent = currentContent.copy(isIdentifyArea = true)
                    content.value = currentContent
                }
            }
        }
    }

    fun checkingExistenceArea(areaTitle: String): Area? {
        val areaLower = areaTitle.lowercase()
        return currentContent.areas.firstOrNull { a -> a.title.lowercase().equals(areaLower) }
    }

    fun cancelTokenSource() = cancellationTokenSource.cancel()

    fun updateChosenArea(selectedArea: Area) {
        chosenArea = selectedArea
    }

    companion object {
        private const val TAG = "LocationViewModel-FP"
    }
}