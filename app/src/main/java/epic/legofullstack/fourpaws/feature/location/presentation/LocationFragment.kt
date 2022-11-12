package epic.legofullstack.fourpaws.feature.location.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.gun0912.tedpermission.TedPermissionUtil
import dagger.hilt.android.AndroidEntryPoint
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.core.domain.model.Area
import epic.legofullstack.fourpaws.databinding.FragmentLocationBinding
import epic.legofullstack.fourpaws.extensions.activityNavController
import epic.legofullstack.fourpaws.feature.base.BaseFragment
import epic.legofullstack.fourpaws.feature.base.ShowDialog
import epic.legofullstack.fourpaws.feature.location.presentation.adapter.AreaAdapter
import epic.legofullstack.fourpaws.feature.location.presentation.dto.LocationViewState

@AndroidEntryPoint
class LocationFragment : BaseFragment(R.layout.fragment_location) {
    private val bindingL by viewBinding(FragmentLocationBinding::bind)
    private val viewModel: LocationViewModel by viewModels()
    private lateinit var areaAdapter: AreaAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner, ::handleViewState)
        viewModel.commands.observe(viewLifecycleOwner, ::handleCommand)

        bindingL.buttonChooseArea.setOnClickListener {
            viewModel.saveUserArea(activityNavController())
        }

        bindingL.autoCompleteTextViewUserArea.setOnItemClickListener { adapterView, _, position, _ ->
            val currentArea = adapterView.adapter.getItem(position) as Area
            viewModel.updateChosenArea(currentArea)
            bindingL.apply {
                autoCompleteTextViewUserArea.setText(currentArea.title)
                buttonChooseArea.isEnabled = true
            }
        }

        bindingL.buttonInitLocation.setOnClickListener { initUserArea() }
    }

    private fun handleViewState(viewState: LocationViewState) {
        refresh(viewState)
        when (viewState) {
            is LocationViewState.Content -> viewState.handle()
            is LocationViewState.Error -> viewState.handle()
            is LocationViewState.Loading -> {
                Log.d(TAG, " Loading")
            }
        }
    }

    private fun LocationViewState.Content.handle() {
        if (isIdentifyArea) {
            if (!userArea.isEmpty()) {
                bindingL.apply {
                    autoCompleteTextViewUserArea.setText(userArea, false)
                    buttonChooseArea.isEnabled = true
                }
            } else {
                viewModel.commands.value = ShowDialog(
                    getString(R.string.error),
                    getString(R.string.permission_error_text),
                    getString(R.string.ok)
                )
            }
        }
        areaAdapter =
            AreaAdapter(bindingL.root.context, R.layout.auto_complete_text_view_item, areas)
        bindingL.autoCompleteTextViewUserArea.setAdapter(areaAdapter)
    }

    private fun LocationViewState.Error.handle() {
        with(bindingL.locationErrorPanel) {
            errorIcon.setImageResource(errorModel.icon)
            errorText.setText(errorModel.title)
        }
    }

    private fun refresh(viewState: LocationViewState) {
        with(bindingL) {
            chooseAreaLayout.isVisible = viewState is LocationViewState.Content
            loadingProgress.isVisible = viewState is LocationViewState.Loading
            locationErrorPanel.root.isVisible = viewState is LocationViewState.Error
        }
    }

    @SuppressLint("MissingPermission")
    fun initUserArea() {
        // todo проверить включен ли gps
        val granted = TedPermissionUtil.isGranted(*PERMISSIONS)
        if (granted) {
            viewModel.initUserArea()
        } else {
            val deniedPermissions = TedPermissionUtil.getDeniedPermissions(*PERMISSIONS)
            viewModel.getPermission(*deniedPermissions.toTypedArray())
        }
    }

    override fun onDestroy() {
        viewModel.cancelTokenSource()
        super.onDestroy()
    }

    companion object {
        private const val TAG = "LocationFragment-FP"
        private var PERMISSIONS =
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
    }
}