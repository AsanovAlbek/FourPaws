package epic.legofullstack.fourpaws.feature.home.domain.usecase

import epic.legofullstack.fourpaws.core.di.DispatchersModule
import epic.legofullstack.fourpaws.core.domain.model.PetFilter
import epic.legofullstack.fourpaws.feature.home.data.mapper.toPet
import epic.legofullstack.fourpaws.feature.home.domain.model.Pet
import epic.legofullstack.fourpaws.feature.home.domain.repository.PetsRepository
import epic.legofullstack.fourpaws.network.errorhandle.ResponseState
import epic.legofullstack.fourpaws.network.errorhandle.safeCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PetFilterUseCase @Inject constructor(
    private val repository: PetsRepository,
    @DispatchersModule.IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(filter: PetFilter): ResponseState<List<Pet>> =
        withContext(ioDispatcher) {
            return@withContext safeCall {
                repository.getPetsByFilter(filter.toPetFilterDto()).map { it.toPet() }
            }
        }
}