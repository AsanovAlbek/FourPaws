package epic.legofullstack.fourpaws.feature.location.domain.repository

import epic.legofullstack.fourpaws.core.di.DispatchersModule
import epic.legofullstack.fourpaws.feature.location.data.GeocoderHandler
import epic.legofullstack.fourpaws.feature.location.data.LocationHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

// todo обработка ошибок
class LocationRepository @Inject constructor(
    private val locationHandler: LocationHandler,
    private val geocoderHandler: GeocoderHandler,
    @DispatchersModule.IoDispatcher
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun getArea() : String? {
        var area: String? = null
        withContext(ioDispatcher) {
            val lastLocation = locationHandler.getLastLocation()
            if (lastLocation != null) {
                area = geocoderHandler.getAddressByGeoLocation(location = lastLocation)?.first()?.subAdminArea
            } else {
                area = geocoderHandler.getAddressByGeoLocation(locationHandler.getCurrentLocation())?.filterNotNull()?.first()?.subAdminArea
            }
        }
        return area
    }
}