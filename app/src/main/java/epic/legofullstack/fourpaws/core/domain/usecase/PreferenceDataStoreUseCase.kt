package epic.legofullstack.fourpaws.core.domain.usecase

import epic.legofullstack.fourpaws.core.domain.repository.CityDataStoreRepository
import kotlinx.coroutines.flow.Flow

class PreferenceDataStoreUseCase(private val dataStore: CityDataStoreRepository) {

    fun getUserCity(): Flow<String?> = dataStore.getUserCity()
}