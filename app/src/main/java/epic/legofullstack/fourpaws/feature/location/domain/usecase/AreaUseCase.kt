package epic.legofullstack.fourpaws.feature.location.domain.usecase

import epic.legofullstack.fourpaws.core.di.DispatchersModule
import epic.legofullstack.fourpaws.feature.location.data.model.toArea
import epic.legofullstack.fourpaws.feature.location.domain.model.Area
import epic.legofullstack.fourpaws.feature.location.domain.repository.AreaRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AreaUseCase @Inject constructor(
    private val areaRepository: AreaRepository,
    @DispatchersModule.IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getAreas(): List<Area> = withContext(ioDispatcher) {
        return@withContext areaRepository.getAreas().map { it.toArea() }
    }

}