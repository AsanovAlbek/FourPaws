package epic.legofullstack.fourpaws.feature_home_page.domain.repository

import epic.legofullstack.fourpaws.feature_home_page.data.model.PetDto
import kotlinx.coroutines.flow.Flow

/**
 * Интерфейс для репозитория
 *
 * @author Asanov Albek in 16.09.2022
 */
interface PetsRepository {
    /** Получение всех питомцев */
    suspend fun getAllPets() : Flow<PetDto>
}