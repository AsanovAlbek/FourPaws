package epic.legofullstack.fourpaws.feature.home.domain.usecase

import epic.legofullstack.fourpaws.core.di.DispatchersModule
import epic.legofullstack.fourpaws.feature.home.domain.repository.PetsRepository
import epic.legofullstack.fourpaws.network.errorhandle.safeCall
import java.net.ConnectException
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetPetByIdUseCase @Inject constructor(
    private val repository: PetsRepository,
    @DispatchersModule.IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(petId: Int) = withContext(ioDispatcher) {
        return@withContext safeCall { TODO( "implementation" ) }
    }
}