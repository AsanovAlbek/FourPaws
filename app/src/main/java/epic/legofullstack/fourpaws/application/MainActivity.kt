package epic.legofullstack.fourpaws.application

import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import epic.legofullstack.fourpaws.BuildConfig
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var  sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("settings_4Paws", AppCompatActivity.MODE_PRIVATE)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)

        if (!foregroundPermissionApproved())
            requestForegroundPermissions()
        val lastCity = sharedPreferences.getString(USER_LAST_CITY, null)
        if (lastCity == null) {
            println("город не известен")
            navGraph.setStartDestination(R.id.definitionCityFlow)
        } else {
            println("город известен: ${lastCity}")
            navGraph.setStartDestination(R.id.mainFlow)
        }
        navController.graph = navGraph
    }

    // todo может надо будет выпилить это
    // проверка предоставления разрешения на геоданные
    private fun foregroundPermissionApproved(): Boolean {
        return PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) &&
                PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(this, android.Manifest.permission.INTERNET)
    }

    //запросить разрешения
    private fun requestForegroundPermissions() {
        if (foregroundPermissionApproved()) {
            Snackbar.make(
                binding.root,
                getString(R.string.permission_rationale),
                Snackbar.LENGTH_LONG
            )
                .setAction(getString(R.string.OK)) {
                    // разрешение получено
                    ActivityCompat.requestPermissions(
                        this@MainActivity,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.INTERNET),
                        PERMISSIONS_REQUEST_CODE
                    )
                }
                .show()
        } else {
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.INTERNET),
                PERMISSIONS_REQUEST_CODE
            )
        }
    }

    // Просмотр разрешений: Обрабатывает результат разрешения.
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d(TAG, "onRequestPermissionResult")

        when (requestCode) {
            PERMISSIONS_REQUEST_CODE -> when {
                grantResults.isEmpty() ->
                    Log.d(TAG, "Взаимодействие с пользователем было отменено")
                grantResults[0] == PackageManager.PERMISSION_GRANTED -> {
                    // Разрешение было получено
                    Log.d(TAG, "Разрешение получено")
                }
                else -> {
                    // отказано
                    Log.d(TAG, "ОТКАЗ")
                    Snackbar.make(
                        binding.root,
                        getString(R.string.permission_denied_explanation),
                        Snackbar.LENGTH_LONG
                    )
                        .setAction(getString(R.string.settings)) {
                            // отображение настроек
                            val intent = Intent()
                            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                            val uri = Uri.fromParts(
                                "package",
                                BuildConfig.APPLICATION_ID,
                                null
                            )
                            intent.data = uri
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                        }
                        .show()
                }
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val USER_LAST_CITY = "LAST_CITY"
        private const val PERMISSIONS_REQUEST_CODE = 34
    }
}