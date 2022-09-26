package epic.legofullstack.fourpaws.core.domain.repository

import epic.legofullstack.fourpaws.core.data.CityPreferenceDataStore
import kotlinx.coroutines.flow.Flow

class CityDataStoreRepository(
    private val cityPreferenceDataStore: CityPreferenceDataStore
) {

    fun getUserCity(): Flow<String?> = cityPreferenceDataStore.getUserCity()
}