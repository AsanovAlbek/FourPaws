package epic.legofullstack.fourpaws.feature.favorites.domain.usecase

import epic.legofullstack.fourpaws.core.di.DispatchersModule
import epic.legofullstack.fourpaws.feature.favorites.data.mapper.toFavoritePet
import epic.legofullstack.fourpaws.feature.favorites.di.FavoritesModule
import epic.legofullstack.fourpaws.feature.favorites.domain.model.FavoritePet
import epic.legofullstack.fourpaws.feature.favorites.domain.repository.FavoritesRepository
import epic.legofullstack.fourpaws.network.errorhandle.safeCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetFavoritePetsUseCase(
    private val repository: FavoritesRepository,
    @DispatchersModule.IoDispatcher
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke() = withContext(ioDispatcher) {
        return@withContext safeCall {
            repository.getFavorites().map { it.toFavoritePet() }.filter { it.isFavorite }
        }
    }
}