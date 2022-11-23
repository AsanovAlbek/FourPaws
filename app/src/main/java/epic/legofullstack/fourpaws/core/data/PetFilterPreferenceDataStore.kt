package epic.legofullstack.fourpaws.core.data

import androidx.datastore.core.DataStore
import epic.legofullstack.fourpaws.PetFilterPreferences
import epic.legofullstack.fourpaws.UserAreaPreferences
import epic.legofullstack.fourpaws.core.data.model.PetFilterDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

class PetFilterPreferenceDataStore(
    private val petFilterDataStore: DataStore<PetFilterPreferences>
) {
    fun getPetFilter(): Flow<PetFilterPreferences> =
        petFilterDataStore.data.catch { ex ->
            if (ex is IOException) {
                emit(PetFilterPreferences.getDefaultInstance())
            }
        }

    suspend fun savePetFilter(filter: PetFilterDto) = petFilterDataStore.updateData { pref ->
        var build = pref.toBuilder()
            .setArea(
                UserAreaPreferences.newBuilder()
                    .setId(filter.area.id)
                    .setTitle(filter.area.title)
                    .build()
            )
            .setPetType(filter.petType ?: "")
            .setGender(filter.gender ?: "")
            .setAge(filter.age ?: "")
            .build()

        val characteristics = filter.characteristics
        if (characteristics != null && characteristics.isNotEmpty())
            build = build.toBuilder().addAllCharacteristics(characteristics).build()
        else
            build = build.toBuilder().clearCharacteristics().build()

        return@updateData build
    }

    suspend fun removePetFilter() = petFilterDataStore.updateData {
        it.toBuilder()
            .clear()
            .build()
    }
}