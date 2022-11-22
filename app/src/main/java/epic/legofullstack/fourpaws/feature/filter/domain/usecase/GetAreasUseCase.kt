package epic.legofullstack.fourpaws.feature.filter.domain.usecase

import epic.legofullstack.fourpaws.feature.filter.domain.repository.FilterRepository
import epic.legofullstack.fourpaws.network.errorhandle.safeCall
import javax.inject.Inject

class GetAreasUseCase @Inject constructor(
    private val repository: FilterRepository
) {
    suspend operator fun invoke() = safeCall {
        repository.getAreas().map { it.toDomain() }
    }
}