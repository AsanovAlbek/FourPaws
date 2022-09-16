package epic.legofullstack.fourpaws.feature_home_page.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import epic.legofullstack.fourpaws.feature_home_page.data.repository.PetsRepositoryImpl
import epic.legofullstack.fourpaws.feature_home_page.data.storage.LocalDataSource
import epic.legofullstack.fourpaws.feature_home_page.domain.repository.PetsRepository
import epic.legofullstack.fourpaws.feature_home_page.domain.usecase.GetAllPetsUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomePageModule {
    @Provides
    @Singleton
    fun providePetsRepository(localDataSource: LocalDataSource) : PetsRepository =
        PetsRepositoryImpl(localDataSource)

    @Provides
    @Singleton
    fun provideLocalDataSource() = LocalDataSource()

    @Provides
    fun provideGetAllPetsUseCase(petsRepository: PetsRepository) : GetAllPetsUseCase =
        GetAllPetsUseCase(petsRepository)


}