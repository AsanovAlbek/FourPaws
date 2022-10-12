package epic.legofullstack.fourpaws.core.data.model

interface RemoteDto<T> {
    fun toDomain(): T
}