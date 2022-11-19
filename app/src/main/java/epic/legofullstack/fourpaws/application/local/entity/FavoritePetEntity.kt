package epic.legofullstack.fourpaws.application.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import epic.legofullstack.fourpaws.application.local.RoomConstants.ADDRESS
import epic.legofullstack.fourpaws.application.local.RoomConstants.AGE
import epic.legofullstack.fourpaws.application.local.RoomConstants.AREA_ID
import epic.legofullstack.fourpaws.application.local.RoomConstants.AREA_NAME
import epic.legofullstack.fourpaws.application.local.RoomConstants.BREED
import epic.legofullstack.fourpaws.application.local.RoomConstants.BREED_ID
import epic.legofullstack.fourpaws.application.local.RoomConstants.CHARACTERISTICS
import epic.legofullstack.fourpaws.application.local.RoomConstants.CITY
import epic.legofullstack.fourpaws.application.local.RoomConstants.COLOR
import epic.legofullstack.fourpaws.application.local.RoomConstants.DESCRIPTIONS
import epic.legofullstack.fourpaws.application.local.RoomConstants.EMAIL
import epic.legofullstack.fourpaws.application.local.RoomConstants.FAVORITES_TABLE
import epic.legofullstack.fourpaws.application.local.RoomConstants.GENDER
import epic.legofullstack.fourpaws.application.local.RoomConstants.ID
import epic.legofullstack.fourpaws.application.local.RoomConstants.IMAGES
import epic.legofullstack.fourpaws.application.local.RoomConstants.LATITUDE
import epic.legofullstack.fourpaws.application.local.RoomConstants.LONGITUDE
import epic.legofullstack.fourpaws.application.local.RoomConstants.NAME
import epic.legofullstack.fourpaws.application.local.RoomConstants.PET_TYPE
import epic.legofullstack.fourpaws.application.local.RoomConstants.PHONE
import epic.legofullstack.fourpaws.application.local.RoomConstants.PREVIEW_IMAGE_URL
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
    @ColumnInfo(name = LATITUDE)
    val latitude: Float = 0f,
    @ColumnInfo(name = LONGITUDE)
    val longitude: Float = 0f,
    @ColumnInfo(name = NAME)
    val name: String = "",
    @ColumnInfo(name = CITY)
    val city: String = "",
    @ColumnInfo(name = AGE)
    val age: String = "",
    @ColumnInfo(name = BREED_ID)
    val breedId: Int = 0,
    @ColumnInfo(name = BREED)
    val breed: String = "",
    @ColumnInfo(name = COLOR)
    val color: String = "",
    @ColumnInfo(name = PET_TYPE)
    val petType: String = "",
    @ColumnInfo(name = GENDER)
    val gender: String = "",
    @ColumnInfo(name = IMAGES)
    val images: List<String> = emptyList(),
    @ColumnInfo(name = PREVIEW_IMAGE_URL)
    val previewImageUrl: String = "",
    @ColumnInfo(name = DESCRIPTIONS)
    val descriptions: String = "",
    @ColumnInfo(name = CHARACTERISTICS)
    val characteristics: List<String> = emptyList()
)