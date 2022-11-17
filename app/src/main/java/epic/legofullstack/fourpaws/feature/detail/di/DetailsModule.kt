package epic.legofullstack.fourpaws.feature.detail.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import epic.legofullstack.fourpaws.application.local.dao.FavoritePetDao
import epic.legofullstack.fourpaws.feature.detail.data.local.DetailsDataSource
import epic.legofullstack.fourpaws.feature.detail.data.repository.DetailsRepositoryImpl
import epic.legofullstack.fourpaws.feature.detail.domain.repository.DetailsRepository
import epic.legofullstack.fourpaws.network.firebase.data.FirebaseDataSource

@Module
@InstallIn(SingletonComponent::class)
class DetailsModule {
    @Provides
    fun provideDetailsRepository(localDataSource: DetailsDataSource): DetailsRepository =
        DetailsRepositoryImpl(localDataSource)

    @Provides
    fun provideLocalDataSource(favoritesDao: FavoritePetDao, remoteSource: FirebaseDataSource) =
        DetailsDataSource(favoritesDao, remoteSource)
}