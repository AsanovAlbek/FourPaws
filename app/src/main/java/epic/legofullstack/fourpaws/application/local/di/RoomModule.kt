package epic.legofullstack.fourpaws.application.local.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import epic.legofullstack.fourpaws.application.local.dao.FavoritePetDao
import epic.legofullstack.fourpaws.application.local.database.FavoritesBase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FavoritesBase = FavoritesBase.createDatabase(context)

    @Provides
    @Singleton
    fun provideDao(database: FavoritesBase): FavoritePetDao = database.favoritesDao()
}