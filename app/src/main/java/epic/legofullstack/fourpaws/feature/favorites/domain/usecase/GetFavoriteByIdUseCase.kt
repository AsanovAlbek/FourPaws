package epic.legofullstack.fourpaws.feature.favorites.domain.usecase

import epic.legofullstack.fourpaws.core.di.DispatchersModule
import epic.legofullstack.fourpaws.feature.favorites.data.mapper.toFavoritePet
import epic.legofullstack.fourpaws.feature.favorites.domain.repository.FavoritesRepository
import epic.legofullstack.fourpaws.network.errorhandle.safeCall
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetFavoriteByIdUseCase @Inject constructor(
    private val repository: FavoritesRepository,
    @DispatchersModule.IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(id: Int) = withContext(ioDispatcher) {
        return@withContext safeCall {
            repository.getFavorites().map { it.toFavoritePet() }.first { pet -> pet.isFavorite }
        }
    }
}