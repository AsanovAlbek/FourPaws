package epic.legofullstack.fourpaws.feature.facts.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import epic.legofullstack.fourpaws.feature.facts.data.repository.FactRepositoryImpl
import epic.legofullstack.fourpaws.feature.facts.domain.repository.FactRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InterestingFactModule {
    @Provides
    @Singleton
    fun provideFactRepository(): FactRepository = FactRepositoryImpl()
}