package epic.legofullstack.fourpaws.feature_favorites.domain.usecase

import epic.legofullstack.fourpaws.feature_favorites.data.mapper.toFavoritePet
import epic.legofullstack.fourpaws.feature_favorites.di.FavoritesModule
import epic.legofullstack.fourpaws.feature_favorites.domain.model.FavoritePet
import epic.legofullstack.fourpaws.feature_favorites.domain.repository.FavoritesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetFavoritePetsUseCase(
    private val repository: FavoritesRepository,
    @FavoritesModule.IoDispatcher
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke() : List<FavoritePet> = withContext(ioDispatcher) {
        return@withContext repository.getFavorites().map { it.toFavoritePet() }.filter { it.isFavorite }
    }
}