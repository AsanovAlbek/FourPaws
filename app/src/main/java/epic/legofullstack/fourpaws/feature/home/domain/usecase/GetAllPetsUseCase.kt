package epic.legofullstack.fourpaws.feature.home.domain.usecase

import epic.legofullstack.fourpaws.dispatchers.DispatchersModule
import epic.legofullstack.fourpaws.feature.home.data.mapper.toPet
import epic.legofullstack.fourpaws.feature.home.di.HomePageModule
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
    suspend operator fun invoke() =
        withContext(ioDispatcher) {
            return@withContext repository.getAllPets().map { it.toPet() }
        }
}