package epic.legofullstack.fourpaws.core.domain.usecase

import epic.legofullstack.fourpaws.core.domain.mapper.toArea
import epic.legofullstack.fourpaws.core.domain.model.Area
import epic.legofullstack.fourpaws.core.domain.model.toAreaModel
import epic.legofullstack.fourpaws.core.domain.repository.UserAreaDataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferenceDataStoreUseCase(private val dataStore: UserAreaDataStoreRepository) {

    fun getUserArea(): Flow<Area> = dataStore.getUserArea().map { pref -> pref.toArea() }

    suspend fun saveUserArea(area: Area) = dataStore.saveUserArea(area.toAreaModel())
}