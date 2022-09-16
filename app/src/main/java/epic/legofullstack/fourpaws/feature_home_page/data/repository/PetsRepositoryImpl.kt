package epic.legofullstack.fourpaws.feature_home_page.data.repository

import epic.legofullstack.fourpaws.feature_home_page.data.model.PetDto
import epic.legofullstack.fourpaws.feature_home_page.data.storage.LocalDataSource
import epic.legofullstack.fourpaws.feature_home_page.domain.repository.PetsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList

/**
 * Реализация интерфейса репозитория
 * В будущем добавится RemoteDataSource для работы с сетью
 * Пока что только LocalDataSource с фейковыми данными
 *
 * @author Asanov Albek in 16.09.2022
 */
class PetsRepositoryImpl(private val localDataSource: LocalDataSource) : PetsRepository {
    override suspend fun getAllPets() : Flow<PetDto> {
        // TODO("Возможно, здесь будут другие операции, например получение из сети")
        return localDataSource.fakeDataFlow()
    }
}