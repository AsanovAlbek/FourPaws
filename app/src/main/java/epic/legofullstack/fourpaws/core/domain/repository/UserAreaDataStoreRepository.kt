package epic.legofullstack.fourpaws.core.domain.repository

import epic.legofullstack.fourpaws.UserAreaPreferences
import epic.legofullstack.fourpaws.core.data.UserAreaPreferenceDataStore
import epic.legofullstack.fourpaws.core.data.model.AreaDto
import kotlinx.coroutines.flow.Flow

class UserAreaDataStoreRepository(
    private val userAreaPreferenceDataStore: UserAreaPreferenceDataStore
) {

    suspend fun saveUserArea(area: AreaDto) = userAreaPreferenceDataStore.saveUserArea(area)

    fun getUserArea(): Flow<UserAreaPreferences> = userAreaPreferenceDataStore.getUserArea()
}