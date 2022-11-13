package epic.legofullstack.fourpaws.application.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import epic.legofullstack.fourpaws.application.local.RoomConstants.ADDRESS
import epic.legofullstack.fourpaws.application.local.RoomConstants.AREA_ID
import epic.legofullstack.fourpaws.application.local.RoomConstants.AREA_NAME
import epic.legofullstack.fourpaws.application.local.RoomConstants.CITY
import epic.legofullstack.fourpaws.application.local.RoomConstants.EMAIL
import epic.legofullstack.fourpaws.application.local.RoomConstants.FAVORITES_TABLE
import epic.legofullstack.fourpaws.application.local.RoomConstants.ID
import epic.legofullstack.fourpaws.application.local.RoomConstants.IS_FAVORITE
import epic.legofullstack.fourpaws.application.local.RoomConstants.NAME
import epic.legofullstack.fourpaws.application.local.RoomConstants.PHONE
import epic.legofullstack.fourpaws.application.local.RoomConstants.SHELTER_ID
import epic.legofullstack.fourpaws.application.local.RoomConstants.SHELTER_NAME

@Entity(tableName = FAVORITES_TABLE)
data class FavoritePetEntity(
    @PrimaryKey
    @ColumnInfo(name = ID)
    val id: Int = 0,
    @ColumnInfo(name = SHELTER_ID)
    val shelterId: Int,
    @ColumnInfo(name = SHELTER_NAME)
    val shelterName: String,
    @ColumnInfo(name = ADDRESS)
    val address: String,
    @ColumnInfo(name = AREA_ID)
    val areaId: Int,
    @ColumnInfo(name = AREA_NAME)
    val areaName: String,
    @ColumnInfo(name = PHONE)
    val phone: String,
    @ColumnInfo(name = EMAIL)
    val email: String,
    @ColumnInfo(name = NAME)
    val name: String = "",
    @ColumnInfo(name = IS_FAVORITE)
    val isFavorite: Boolean = false,
    @ColumnInfo(name = CITY)
    val city: String = ""
)