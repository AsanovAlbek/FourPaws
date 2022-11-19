package epic.legofullstack.fourpaws.application.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import epic.legofullstack.fourpaws.application.local.RoomConstants.FAVORITES_TABLE
import epic.legofullstack.fourpaws.application.local.entity.FavoritePetEntity

@Dao
abstract class FavoritePetDao {
    /** Получить всех питомцев из избранного*/
    @Query("Select * from $FAVORITES_TABLE")
    abstract fun getFavorites(): List<FavoritePetEntity>

    @Query("Select * from $FAVORITES_TABLE where id = :id")
    abstract fun findFavorite(id: Int): FavoritePetEntity

    /** Добавить питомца в избранное */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun addFavorite(addedPet: FavoritePetEntity)

    /** Удалить питомца из избранного */
    @Delete
    abstract fun removeFavorite(removedPet: FavoritePetEntity)

    @Query("Select exists (Select id from $FAVORITES_TABLE where id = :petId)")
    abstract fun isExist(petId: Int): Boolean
}