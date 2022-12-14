package epic.legofullstack.fourpaws.core.di

import android.content.Context
import androidx.datastore.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import epic.legofullstack.fourpaws.core.data.PetFilterPreferenceDataStore
import epic.legofullstack.fourpaws.core.data.PetFilterPreferencesSerializer
import epic.legofullstack.fourpaws.core.data.UserAreaPreferenceDataStore
import epic.legofullstack.fourpaws.core.data.UserAreaPreferencesSerializer
import epic.legofullstack.fourpaws.core.domain.repository.PetFilterDataStoreRepository
import epic.legofullstack.fourpaws.core.domain.repository.UserAreaDataStoreRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PreferenceModule {
    private val Context.userAreaDataStore by dataStore(fileName = USER_AREA_PREFERENCES, serializer = UserAreaPreferencesSerializer)
    private val Context.filterPetDataStore by dataStore(fileName = FILTER_PET_PREFERENCES, serializer = PetFilterPreferencesSerializer)


    @Provides
    @Singleton
    fun provideCityDataStoreRepository(userAreaPreferenceDataStore: UserAreaPreferenceDataStore) =
        UserAreaDataStoreRepository(userAreaPreferenceDataStore)

    @Singleton
    @Provides
    fun provideUserAreaPreferenceDataStore(@ApplicationContext context:  Context): UserAreaPreferenceDataStore =
       UserAreaPreferenceDataStore(context.userAreaDataStore)

    @Provides
    @Singleton
    fun providePetFilterPreferenceDataStore(@ApplicationContext context: Context): PetFilterPreferenceDataStore =
        PetFilterPreferenceDataStore(context.filterPetDataStore)

    @Singleton
    @Provides
    fun providePetFilterDataStoreRepository(filterPetDataStore: PetFilterPreferenceDataStore) =
        PetFilterDataStoreRepository(filterPetDataStore)

    companion object {
        private const val USER_AREA_PREFERENCES = "user_area_pref.pb"
        private const val FILTER_PET_PREFERENCES = "filter_pet_pref.pb"
    }
}
