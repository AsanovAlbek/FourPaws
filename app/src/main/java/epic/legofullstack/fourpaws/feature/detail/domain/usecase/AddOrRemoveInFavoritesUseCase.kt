package epic.legofullstack.fourpaws.feature.detail.domain.usecase

import epic.legofullstack.fourpaws.core.di.DispatchersModule
import epic.legofullstack.fourpaws.feature.detail.data.mapper.toPet
import epic.legofullstack.fourpaws.feature.detail.data.mapper.toPetDto
import epic.legofullstack.fourpaws.feature.detail.domain.model.Pet
import epic.legofullstack.fourpaws.feature.detail.domain.repository.DetailsRepository
import epic.legofullstack.fourpaws.network.errorhandle.safeCall
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class AddOrRemoveInFavoritesUseCase @Inject constructor(
    private val repository: DetailsRepository,
    @DispatchersModule.IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun addPetToFavorites(pet: Pet) = withContext(ioDispatcher) {
        return@withContext safeCall { repository.addToFavorite(pet.toPetDto()).toPet() }
    }

    suspend fun removePetToFavorites(pet: Pet) = withContext(ioDispatcher) {
        return@withContext safeCall { repository.removePetFromFavorite(pet.toPetDto()).toPet() }
    }
}