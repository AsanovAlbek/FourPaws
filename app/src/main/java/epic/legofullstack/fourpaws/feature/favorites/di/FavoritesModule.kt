package epic.legofullstack.fourpaws.feature.favorites.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import epic.legofullstack.fourpaws.feature.favorites.data.repository.FavoritesRepositoryImpl
import epic.legofullstack.fourpaws.feature.favorites.data.storage.LocalDataSource
import epic.legofullstack.fourpaws.feature.favorites.domain.repository.FavoritesRepository
import epic.legofullstack.fourpaws.feature.favorites.domain.usecase.GetFavoritePetsUseCase
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
}