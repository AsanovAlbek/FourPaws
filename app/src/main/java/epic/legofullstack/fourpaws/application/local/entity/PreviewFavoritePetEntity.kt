package epic.legofullstack.fourpaws.application.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import epic.legofullstack.fourpaws.application.local.RoomConstants.CITY
import epic.legofullstack.fourpaws.application.local.RoomConstants.FAVORITES_PREVIEW_TABLE
import epic.legofullstack.fourpaws.application.local.RoomConstants.GENDER
import epic.legofullstack.fourpaws.application.local.RoomConstants.ID
import epic.legofullstack.fourpaws.application.local.RoomConstants.NAME
import epic.legofullstack.fourpaws.application.local.RoomConstants.PREVIEW_IMAGE_URL

@Entity(tableName = FAVORITES_PREVIEW_TABLE)
data class PreviewFavoritePetEntity(
    @PrimaryKey
    @ColumnInfo(name = ID)
    val id: Int = 0,
    @ColumnInfo(name = NAME)
    val name: String = "",
    @ColumnInfo(name = CITY)
    val city: String = "",
    @ColumnInfo(name = GENDER)
    val gender: String = "",
    @ColumnInfo(name = PREVIEW_IMAGE_URL)
    val previewImg: String = "",
)