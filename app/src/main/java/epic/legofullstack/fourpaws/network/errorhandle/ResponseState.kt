package epic.legofullstack.fourpaws.network.errorhandle

sealed class ResponseState<out R> {
    data class Success<out T>(val data : T) : ResponseState<T>()
    data class Error(val isNetworkError: Boolean) : ResponseState<Nothing>()
}

val <T> ResponseState<T>.data : T?
    get() = (this as? ResponseState.Success)?.data