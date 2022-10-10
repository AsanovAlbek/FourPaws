package epic.legofullstack.fourpaws.feature.location.domain.usecase

import epic.legofullstack.fourpaws.core.di.DispatchersModule
import epic.legofullstack.fourpaws.feature.location.domain.repository.LocationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserLocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository,
    @DispatchersModule.IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
    ) {
    suspend fun getUserArea(): String? = withContext(ioDispatcher){
       return@withContext locationRepository.getArea()
    }
}