package epic.legofullstack.fourpaws.core.domain.repository

import epic.legofullstack.fourpaws.PetFilterPreferences
import epic.legofullstack.fourpaws.core.data.PetFilterPreferenceDataStore
import epic.legofullstack.fourpaws.core.data.model.PetFilterDto
import kotlinx.coroutines.flow.Flow

class PetFilterDataStoreRepository(private val petFilterPreferenceDataStore: PetFilterPreferenceDataStore) {
    suspend fun savePetFilter(filter: PetFilterDto) = petFilterPreferenceDataStore.savePetFilter(filter)

    fun getPetFilter(): Flow<PetFilterPreferences> = petFilterPreferenceDataStore.getPetFilter()

    suspend fun removePetFilter() = petFilterPreferenceDataStore.removePetFilter()
}