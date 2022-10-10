package epic.legofullstack.fourpaws.feature.location.data

import epic.legofullstack.fourpaws.feature.location.data.model.AreaDto
import epic.legofullstack.fourpaws.feature.location.domain.repository.AreaRepository

//TODO получать данные извне
class AreaRepositoryImpl : AreaRepository {
    override suspend fun getAreas(): List<AreaDto> {
        return listOf(
            AreaDto(1, "Komi Republic"),
            AreaDto(2, "Krasnodar Territory"),
            AreaDto(3, "Amur Region"),
            AreaDto(4, "Moscow Region"),
            AreaDto(5, "Santa Clara County"))
    }

}