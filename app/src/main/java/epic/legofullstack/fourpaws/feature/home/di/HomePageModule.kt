package epic.legofullstack.fourpaws.feature.home.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import epic.legofullstack.fourpaws.feature.home.data.repository.PetsRepositoryImpl
import epic.legofullstack.fourpaws.feature.home.data.storage.LocalDataSource
import epic.legofullstack.fourpaws.feature.home.domain.repository.PetsRepository
import epic.legofullstack.fourpaws.feature.home.domain.usecase.GetAllPetsUseCase
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher


@Module
@InstallIn(SingletonComponent::class)
object HomePageModule {
    @Provides
    @Singleton
    fun providePetsRepository(localDataSource: LocalDataSource) : PetsRepository =
        PetsRepositoryImpl(localDataSource)

    @Provides
    @Singleton
    fun provideLocalDataSource(): LocalDataSource = LocalDataSource()

    @Provides
    fun provideGetAllPetsUseCase(petsRepository: PetsRepository) : GetAllPetsUseCase =
        GetAllPetsUseCase(petsRepository)

}