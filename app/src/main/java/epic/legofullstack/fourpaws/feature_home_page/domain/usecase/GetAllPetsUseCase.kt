package epic.legofullstack.fourpaws.feature_home_page.domain.usecase

import epic.legofullstack.fourpaws.feature_home_page.data.mapper.toPet
import epic.legofullstack.fourpaws.feature_home_page.domain.repository.PetsRepository
import kotlinx.coroutines.flow.map

/**
 * Use case для получения всех питомцев
 *
 * @author Asanov Albek in 16.09.2022
 */
class GetAllPetsUseCase(private val repository: PetsRepository) {
    suspend fun invoke() = repository.getAllPets().map { it.toPet() }
}