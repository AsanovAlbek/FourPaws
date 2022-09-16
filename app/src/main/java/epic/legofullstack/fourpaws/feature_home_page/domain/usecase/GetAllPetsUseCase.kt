package epic.legofullstack.fourpaws.feature_home_page.domain.usecase

import epic.legofullstack.fourpaws.feature_home_page.data.mapper.toPet
import epic.legofullstack.fourpaws.feature_home_page.domain.repository.PetsRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

/**
 * Use case для получения всех питомцев
 *
 * @author Asanov Albek in 16.09.2022
 */
class GetAllPetsUseCase(private val repository: PetsRepository) {
    suspend operator fun invoke() = repository.getAllPets().map { it.toPet() }
}