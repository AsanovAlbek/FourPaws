package epic.legofullstack.fourpaws.core.data

import androidx.datastore.core.DataStore
import epic.legofullstack.fourpaws.UserAreaPreferences
import epic.legofullstack.fourpaws.core.data.model.AreaDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException


class UserAreaPreferenceDataStore(
    private val userAreaDataStore: DataStore<UserAreaPreferences>
) {

    fun getUserArea(): Flow<UserAreaPreferences> =
        userAreaDataStore.data.catch { ex -> if (ex is IOException) emit(UserAreaPreferences.getDefaultInstance()) }

    suspend fun saveUserArea(area: AreaDto) = userAreaDataStore.updateData { preference ->
        preference.toBuilder().setId(area.id).setTitle(area.title).build()
    }
}