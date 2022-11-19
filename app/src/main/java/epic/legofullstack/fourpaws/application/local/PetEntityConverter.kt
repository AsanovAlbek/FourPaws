package epic.legofullstack.fourpaws.application.local

import androidx.room.TypeConverter

class PetEntityConverter {
    @TypeConverter
    fun convertToList(value: String): List<String> =
        value.split(DELIMITER)

    @TypeConverter
    fun convertToString(list: List<String>): String =
        list.joinToString(separator = DELIMITER)

    companion object {
        const val DELIMITER = ", "
    }
}