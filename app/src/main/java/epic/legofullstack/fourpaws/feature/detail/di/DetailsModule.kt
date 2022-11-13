package epic.legofullstack.fourpaws.feature.detail.di

import androidx.room.Dao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import epic.legofullstack.fourpaws.application.local.dao.FavoritePetDao
import epic.legofullstack.fourpaws.feature.detail.data.local.DetailsLocalDataSource
import epic.legofullstack.fourpaws.feature.detail.data.repository.DetailsRepositoryImpl
import epic.legofullstack.fourpaws.feature.detail.domain.repository.DetailsRepository

@Module
@InstallIn(SingletonComponent::class)
class DetailsModule {
    @Provides
    fun provideDetailsRepository(localDataSource: DetailsLocalDataSource): DetailsRepository =
        DetailsRepositoryImpl(localDataSource)

    @Provides
    fun provideLocalDataSource(favoritesDao: FavoritePetDao) = DetailsLocalDataSource(favoritesDao)
}