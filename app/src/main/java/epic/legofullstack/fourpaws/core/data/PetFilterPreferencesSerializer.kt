package epic.legofullstack.fourpaws.core.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import epic.legofullstack.fourpaws.PetFilterPreferences
import java.io.InputStream
import java.io.OutputStream

object PetFilterPreferencesSerializer : Serializer<PetFilterPreferences> {
    override val defaultValue: PetFilterPreferences = PetFilterPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): PetFilterPreferences {
        try {
            return PetFilterPreferences.parseFrom(input)
        } catch (ex: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", ex)
        }
    }

    override suspend fun writeTo(t: PetFilterPreferences, output: OutputStream) = t.writeTo(output)
}