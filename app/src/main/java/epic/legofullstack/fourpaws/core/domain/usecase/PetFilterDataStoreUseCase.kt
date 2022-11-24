package epic.legofullstack.fourpaws.core.domain.usecase

import epic.legofullstack.fourpaws.core.di.DispatchersModule
import epic.legofullstack.fourpaws.core.domain.mapper.toPetFilter
import epic.legofullstack.fourpaws.core.domain.model.PetFilter
import epic.legofullstack.fourpaws.core.domain.repository.PetFilterDataStoreRepository
import epic.legofullstack.fourpaws.network.errorhandle.ResponseState
import epic.legofullstack.fourpaws.network.errorhandle.safeCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PetFilterDataStoreUseCase @Inject constructor(
    private val dataStore: PetFilterDataStoreRepository,
    @DispatchersModule.IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getPetFilter(): ResponseState<PetFilter> = safeCall {
        withContext(ioDispatcher) {
            return@withContext dataStore.getPetFilter().map { it.toPetFilter() }.first()
        }
    }

    suspend fun savePetFilter(filter: PetFilter) = safeCall {
        dataStore.savePetFilter(filter.toPetFilterDto())
    }
}