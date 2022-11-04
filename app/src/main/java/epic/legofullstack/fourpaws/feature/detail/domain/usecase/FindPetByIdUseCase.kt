package epic.legofullstack.fourpaws.feature.detail.domain.usecase

import epic.legofullstack.fourpaws.core.di.DispatchersModule
import epic.legofullstack.fourpaws.feature.detail.data.mapper.toPet
import epic.legofullstack.fourpaws.feature.detail.domain.repository.DetailsRepository
import epic.legofullstack.fourpaws.network.errorhandle.safeCall
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class FindPetByIdUseCase @Inject constructor(
    private val repository: DetailsRepository,
    @DispatchersModule.IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(id: Int) = withContext(ioDispatcher) {
        return@withContext safeCall { repository.findPetById(id).toPet() }
    }
}