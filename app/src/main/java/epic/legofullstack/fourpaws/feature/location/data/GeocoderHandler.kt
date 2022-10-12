package epic.legofullstack.fourpaws.feature.location.data

import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.annotation.WorkerThread
import javax.inject.Inject

class GeocoderHandler @Inject constructor(private val geocoder: Geocoder) {
    @WorkerThread
    fun getAddressByGeoLocation(location: Location, maxResults: Int = 1): MutableList<Address>? {
        return geocoder.getFromLocation(location.latitude, location.longitude, maxResults)
    }
}