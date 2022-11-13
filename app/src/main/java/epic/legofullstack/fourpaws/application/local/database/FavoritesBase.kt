package epic.legofullstack.fourpaws.application.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import epic.legofullstack.fourpaws.application.local.RoomConstants.FAVORITES_DATABASE
import epic.legofullstack.fourpaws.application.local.dao.FavoritePetDao
import epic.legofullstack.fourpaws.application.local.entity.FavoritePetEntity

@Database(
    entities = [FavoritePetEntity::class], exportSchema = false, version = 1
)
abstract class FavoritesBase: RoomDatabase() {
    abstract fun favoritesDao(): FavoritePetDao
    companion object {
        fun createDatabase(context: Context) =
            Room.databaseBuilder(context, FavoritesBase::class.java, FAVORITES_DATABASE)
                .fallbackToDestructiveMigration()
                .build()
    }
}