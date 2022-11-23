package epic.legofullstack.fourpaws.feature.home.data.repository

import epic.legofullstack.fourpaws.core.data.model.PetFilterDto
import epic.legofullstack.fourpaws.feature.home.data.mapper.toHome
import epic.legofullstack.fourpaws.feature.home.data.model.HomePetDto
import epic.legofullstack.fourpaws.feature.home.data.storage.HomePageDataSource
import epic.legofullstack.fourpaws.feature.home.domain.repository.PetsRepository

/**
 * Реализация интерфейса репозитория
 * В будущем добавится RemoteDataSource для работы с сетью
 * Пока что только LocalDataSource с фейковыми данными
 *
 * @author Asanov Albek in 16.09.2022
 */
class PetsRepositoryImpl(
    private val localDataSource: HomePageDataSource
) : PetsRepository {
    override suspend fun getAllPets(areaId: Int): List<HomePetDto> =
        localDataSource.getPetsByArea(areaId)

    override suspend fun getPetsByFilter(filter: PetFilterDto): List<HomePetDto> =
        localDataSource.getPetsByFilter(filter.area.id, filter).map { it.toHome() }
}