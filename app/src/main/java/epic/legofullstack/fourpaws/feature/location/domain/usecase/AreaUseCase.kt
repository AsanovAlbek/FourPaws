package epic.legofullstack.fourpaws.feature.location.domain.usecase

import epic.legofullstack.fourpaws.core.di.DispatchersModule
import epic.legofullstack.fourpaws.core.domain.model.Area
import epic.legofullstack.fourpaws.feature.location.domain.repository.AreaRepository
import epic.legofullstack.fourpaws.network.errorhandle.ResponseState
import epic.legofullstack.fourpaws.network.errorhandle.safeCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AreaUseCase @Inject constructor(
    private val areaRepository: AreaRepository,
    @DispatchersModule.IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getAreas(): ResponseState<List<Area>> = withContext(ioDispatcher) {
        return@withContext safeCall {  areaRepository.getAreas().map { it.toDomain() }}
    }

}