package epic.legofullstack.fourpaws.feature.detail.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
    fun provideLocalDataSource() = DetailsLocalDataSource()
}