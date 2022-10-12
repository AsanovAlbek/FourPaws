package epic.legofullstack.fourpaws.feature.location.data

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LocationHandler @Inject constructor(private val fusedLocationClient: FusedLocationProviderClient, private val cancellationTokenSource: CancellationTokenSource) {


    @SuppressLint("MissingPermission")
    suspend fun getLastLocation(): Location? = fusedLocationClient.lastLocation.await()

    @SuppressLint("MissingPermission")
    suspend fun getCurrentLocation(): Location = fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, cancellationTokenSource.token).await()
}