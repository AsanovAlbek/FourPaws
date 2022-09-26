package epic.legofullstack.fourpaws.core.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import epic.legofullstack.fourpaws.core.data.CityPreferenceDataStore
import epic.legofullstack.fourpaws.core.domain.repository.CityDataStoreRepository
import epic.legofullstack.fourpaws.core.domain.usecase.PreferenceDataStoreUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PreferenceModule {
    private val Context.dataStore by preferencesDataStore(name = SETTINGS_FOUR_PAWS)


    @Provides
    @Singleton
    fun provideCityDataStoreRepository(cityPreferenceDataStore: CityPreferenceDataStore) =
        CityDataStoreRepository(cityPreferenceDataStore)

    @Provides
    @Singleton
    fun providePreferenceDataStoreUseCase(cityDataStoreRepository: CityDataStoreRepository) =
        PreferenceDataStoreUseCase(cityDataStoreRepository)

    @Singleton
    @Provides
    fun provideCityPreferenceDataStore(@ApplicationContext context:  Context): CityPreferenceDataStore =
       CityPreferenceDataStore(context.dataStore)


    companion object {
        private const val SETTINGS_FOUR_PAWS = "SETTINGS_FOUR_PAWS"
    }
}
