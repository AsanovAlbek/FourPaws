package epic.legofullstack.fourpaws.feature.filter.data.repository

import epic.legofullstack.fourpaws.feature.filter.domain.repository.FilterRepository

class FilterRepositoryImpl(
    private val source: FilterDataSource
): FilterRepository {
    override suspend fun getAreas() = source.getAreas()
}