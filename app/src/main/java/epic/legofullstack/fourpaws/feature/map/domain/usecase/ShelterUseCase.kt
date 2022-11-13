package epic.legofullstack.fourpaws.feature.map.domain.usecase

import epic.legofullstack.fourpaws.core.di.DispatchersModule
import epic.legofullstack.fourpaws.feature.map.domain.mapper.toShelter
import epic.legofullstack.fourpaws.feature.map.domain.model.Shelter
import epic.legofullstack.fourpaws.feature.map.domain.repository.ShelterRepository
import epic.legofullstack.fourpaws.network.errorhandle.ResponseState
import epic.legofullstack.fourpaws.network.errorhandle.safeCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShelterUseCase @Inject constructor(
    private val shelterRepository: ShelterRepository,
    @DispatchersModule.IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getShelterByAreaId(areaId: Int): ResponseState<List<Shelter>> = withContext(ioDispatcher) {
        return@withContext safeCall { shelterRepository.getShelterByAreaId(areaId).map { it.toShelter() } }
    }
}