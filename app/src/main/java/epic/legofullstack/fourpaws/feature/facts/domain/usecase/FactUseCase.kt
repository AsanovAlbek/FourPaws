package epic.legofullstack.fourpaws.feature.facts.domain.usecase

import epic.legofullstack.fourpaws.core.di.DispatchersModule
import epic.legofullstack.fourpaws.feature.facts.domain.model.Fact
import epic.legofullstack.fourpaws.feature.facts.domain.model.FactPreview
import epic.legofullstack.fourpaws.feature.facts.domain.repository.FactRepository
import epic.legofullstack.fourpaws.network.errorhandle.ResponseState
import epic.legofullstack.fourpaws.network.errorhandle.safeCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FactUseCase @Inject constructor(
    private val factRepository: FactRepository,
    @DispatchersModule.IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getFacts(): ResponseState<List<FactPreview>> = withContext(ioDispatcher) {
        return@withContext safeCall { factRepository.getFacts().map { it.toDomain() } }
    }

    suspend fun getFactById(factId: Int): ResponseState<Fact> = withContext(ioDispatcher){
        return@withContext safeCall { factRepository.getFactById(factId).toDomain() }
    }
}