package epic.legofullstack.fourpaws.feature.detail.domain.usecase

import epic.legofullstack.fourpaws.core.di.DispatchersModule
import epic.legofullstack.fourpaws.feature.detail.data.mapper.toDetails
import epic.legofullstack.fourpaws.feature.detail.domain.model.PetDetail
import epic.legofullstack.fourpaws.feature.detail.domain.repository.DetailsRepository
import epic.legofullstack.fourpaws.network.errorhandle.safeCall
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class AddOrRemoveInFavoritesUseCase @Inject constructor(
    private val repository: DetailsRepository,
    @DispatchersModule.IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun addPetToFavorites(pet: PetDetail) = withContext(ioDispatcher) {
        return@withContext safeCall { repository.addToFavorite(pet.toDetails()) }
    }

    suspend fun removePetToFavorites(pet: PetDetail) = withContext(ioDispatcher) {
        return@withContext safeCall { repository.removePetFromFavorite(pet.toDetails()) }
    }

    suspend fun isFavorite(pet: PetDetail) = withContext(ioDispatcher) {
        repository.isFavorite(pet.toDetails())
    }
}