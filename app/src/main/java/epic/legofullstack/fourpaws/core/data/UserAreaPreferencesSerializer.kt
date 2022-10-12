package epic.legofullstack.fourpaws.core.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import epic.legofullstack.fourpaws.UserAreaPreferences
import java.io.InputStream
import java.io.OutputStream

object UserAreaPreferencesSerializer : Serializer<UserAreaPreferences> {
    override val defaultValue: UserAreaPreferences = UserAreaPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserAreaPreferences {
       try{
           return UserAreaPreferences.parseFrom(input)
       } catch (ex: InvalidProtocolBufferException) {
           throw CorruptionException("Cannot read proto.", ex)
       }
    }

    override suspend fun writeTo(t: UserAreaPreferences, output: OutputStream) = t.writeTo(output)
}