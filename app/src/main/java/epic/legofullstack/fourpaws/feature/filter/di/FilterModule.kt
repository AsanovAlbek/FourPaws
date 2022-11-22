package epic.legofullstack.fourpaws.feature.filter.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import epic.legofullstack.fourpaws.feature.filter.data.repository.FilterDataSource
import epic.legofullstack.fourpaws.feature.filter.data.repository.FilterRepositoryImpl
import epic.legofullstack.fourpaws.feature.filter.domain.repository.FilterRepository
import epic.legofullstack.fourpaws.network.firebase.data.FirebaseDataSource

@Module
@InstallIn(SingletonComponent::class)
class FilterModule {
    fun provideDataSource(firebaseDataSource: FirebaseDataSource) =
        FilterDataSource(firebaseDataSource)

    fun provideRepository(source: FilterDataSource): FilterRepository =
        FilterRepositoryImpl(source)
}