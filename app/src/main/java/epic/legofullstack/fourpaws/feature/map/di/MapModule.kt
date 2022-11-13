package epic.legofullstack.fourpaws.feature.map.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import epic.legofullstack.fourpaws.feature.map.data.repository.ShelterRepositoryImpl
import epic.legofullstack.fourpaws.feature.map.domain.repository.ShelterRepository
import epic.legofullstack.fourpaws.network.firebase.data.FirebaseDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapModule {
    @Provides
    @Singleton
    fun provideShelterRepository(firebaseDataSource: FirebaseDataSource): ShelterRepository = ShelterRepositoryImpl(firebaseDataSource)
}