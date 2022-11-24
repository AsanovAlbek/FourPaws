package epic.legofullstack.fourpaws.feature.home.domain.repository

import epic.legofullstack.fourpaws.core.data.model.PetFilterDto
import epic.legofullstack.fourpaws.feature.home.data.model.HomePetDto

/**
 * Интерфейс для репозитория
 *
 * @author Asanov Albek in 16.09.2022
 */
interface PetsRepository {
    /** Получение всех питомцев */
    suspend fun getAllPets(areaId: Int) : List<HomePetDto>

    /**
     * поиск питомцев по фильтру
     */
    suspend fun getPetsByFilter(filter: PetFilterDto): List<HomePetDto>
}