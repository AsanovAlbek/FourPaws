package epic.legofullstack.fourpaws.feature.home.domain.usecase

import epic.legofullstack.fourpaws.core.di.DispatchersModule
import epic.legofullstack.fourpaws.network.errorhandle.ResponseState
import epic.legofullstack.fourpaws.network.errorhandle.safeCall
import epic.legofullstack.fourpaws.feature.home.data.mapper.toPet
import epic.legofullstack.fourpaws.feature.home.domain.model.Pet
import epic.legofullstack.fourpaws.feature.home.domain.repository.PetsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Use case для получения всех питомцев
 *
 * @author Asanov Albek in 16.09.2022
 */
class GetAllPetsUseCase(
    private val repository: PetsRepository,
    @DispatchersModule.IoDispatcher
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {
    suspend operator fun invoke() : ResponseState<List<Pet>> = withContext(ioDispatcher) {
            return@withContext safeCall { repository.getAllPets().map { it.toPet() } }
        }
}