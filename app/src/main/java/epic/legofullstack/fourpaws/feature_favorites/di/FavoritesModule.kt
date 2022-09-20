package epic.legofullstack.fourpaws.feature_favorites.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import epic.legofullstack.fourpaws.feature_favorites.data.repository.FavoritesRepositoryImpl
import epic.legofullstack.fourpaws.feature_favorites.data.storage.LocalDataSource
import epic.legofullstack.fourpaws.feature_favorites.domain.repository.FavoritesRepository
import epic.legofullstack.fourpaws.feature_favorites.domain.usecase.GetFavoritePetsUseCase
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FavoritesModule {
    @Provides
    @Singleton
    fun provideLocalDataSource() = LocalDataSource()

    @Provides
    @Singleton
    fun provideFavoritesRepository(localDataSource: LocalDataSource) : FavoritesRepository =
        FavoritesRepositoryImpl(localDataSource)

    @Provides
    fun provideGetFavoriteUseCase(repository: FavoritesRepository) =
        GetFavoritePetsUseCase(repository)

    @Qualifier
    @Retention
    annotation class IoDispatcher
}