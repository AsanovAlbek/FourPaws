package epic.legofullstack.fourpaws.feature.favorites.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import epic.legofullstack.fourpaws.application.local.dao.FavoritePetDao
import epic.legofullstack.fourpaws.feature.favorites.data.repository.FavoritesRepositoryImpl
import epic.legofullstack.fourpaws.feature.favorites.data.storage.FavoriteLocalDataSource
import epic.legofullstack.fourpaws.feature.favorites.domain.repository.FavoritesRepository
import epic.legofullstack.fourpaws.feature.favorites.domain.usecase.GetFavoritePetsUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FavoritesModule {
    @Provides
    @Singleton
    fun provideFavoriteLocalDataSource(favoritesDao: FavoritePetDao) = FavoriteLocalDataSource(favoritesDao)

    @Provides
    @Singleton
    fun provideFavoritesRepository(localDataSource: FavoriteLocalDataSource): FavoritesRepository =
        FavoritesRepositoryImpl(localDataSource)

    @Provides
    @Singleton
    fun provideGetFavoritePetsUseCase(repository: FavoritesRepository) : GetFavoritePetsUseCase = GetFavoritePetsUseCase(repository)
}