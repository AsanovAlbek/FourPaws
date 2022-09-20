package epic.legofullstack.fourpaws.feature_home_page.domain.usecase

import epic.legofullstack.fourpaws.feature_home_page.data.mapper.toPet
import epic.legofullstack.fourpaws.feature_home_page.di.HomePageModule
import epic.legofullstack.fourpaws.feature_home_page.domain.repository.PetsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext

/**
 * Use case для получения всех питомцев
 *
 * @author Asanov Albek in 16.09.2022
 */
class GetAllPetsUseCase(
    private val repository: PetsRepository,
    @HomePageModule.IoDispatcher
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {
    suspend operator fun invoke() =
        withContext(ioDispatcher) {
            return@withContext repository.getAllPets().map { it.toPet() }
        }
}