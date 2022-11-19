package epic.legofullstack.fourpaws.feature.detail.presentation

import android.content.Context
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.network.firebase.data.model.Age

fun Age.mapAge(context: Context) = when(this) {
    Age.ADULT -> context.getString(R.string.adult)
    Age.BABY -> context.getString(R.string.baby)
    Age.ELDERLY -> context.getString(R.string.elderly)
    Age.YOUNG -> context.getString(R.string.young)
    Age.UNKNOWN -> context.getString(R.string.unknown_age)
}

fun List<String>.mapCharacteristics(context: Context) = map {
    it.mapOneCharacteristic(context)
}

private fun String.mapOneCharacteristic(context: Context): String =
    when(this) {
        context.getString(R.string.vaccinated) -> context.getString(R.string.ru_vaccinated)
        context.getString(R.string.friendly) -> context.getString(R.string.ru_friendly)
        context.getString(R.string.sterilized) -> context.getString(R.string.ru_sterilised)
        context.getString(R.string.accustomed_tray) -> context.getString(R.string.ru_accustomed_tray)
        else -> ""
    }

fun String.mapGender(context: Context): String =
    when(this) {
        context.getString(R.string.male) -> context.getString(R.string.ru_male)
        context.getString(R.string.female) -> context.getString(R.string.ru_female)
        else -> ""
    }