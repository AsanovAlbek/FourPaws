package epic.legofullstack.fourpaws.core.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class CityPreferenceDataStore(
    private val dataStore: DataStore<Preferences>
) {

    fun getUserCity(): Flow<String?>  {
       return  dataStore.data.map { it[USER_CITY] }
    }

    suspend fun saveUserCity(value: String) =
        dataStore.edit{
            it[USER_CITY] = value
        }


    companion object {
        private val USER_CITY = stringPreferencesKey("USER_CITY")
    }
}