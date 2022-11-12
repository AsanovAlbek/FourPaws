package epic.legofullstack.fourpaws.feature.home.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import epic.legofullstack.fourpaws.feature.home.data.repository.PetsRepositoryImpl
import epic.legofullstack.fourpaws.feature.home.data.storage.HomePageLocalDataSource
import epic.legofullstack.fourpaws.feature.home.domain.repository.PetsRepository
import epic.legofullstack.fourpaws.feature.home.domain.usecase.GetAllPetsUseCase
import epic.legofullstack.fourpaws.network.firebase.data.FirebaseDataSource
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object HomePageModule {
    @Provides
    @Singleton
    fun providePetsRepository(localDataSource: HomePageLocalDataSource, firebaseDataSource: FirebaseDataSource) : PetsRepository =
        PetsRepositoryImpl(localDataSource, firebaseDataSource)

    @Provides
    @Singleton
    fun provideHomePageLocalDataSource(): HomePageLocalDataSource = HomePageLocalDataSource()

    @Provides
    fun provideGetAllPetsUseCase(petsRepository: PetsRepository) : GetAllPetsUseCase =
        GetAllPetsUseCase(petsRepository)

}