package epic.legofullstack.fourpaws.application.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import epic.legofullstack.fourpaws.application.local.PetEntityConverter
import epic.legofullstack.fourpaws.application.local.RoomConstants.FAVORITES_DATABASE
import epic.legofullstack.fourpaws.application.local.dao.FavoritePetDao
import epic.legofullstack.fourpaws.application.local.entity.FavoritePetEntity
import epic.legofullstack.fourpaws.application.local.entity.PreviewFavoritePetEntity

@Database(
    entities = [FavoritePetEntity::class, PreviewFavoritePetEntity::class],
    exportSchema = false,
    version = 1
)
@TypeConverters(PetEntityConverter::class)
abstract class FavoritesBase: RoomDatabase() {
    abstract fun favoritesDao(): FavoritePetDao
    companion object {
        fun createDatabase(context: Context) =
            Room.databaseBuilder(context, FavoritesBase::class.java, FAVORITES_DATABASE)
                .fallbackToDestructiveMigration()
                .build()
    }
}