package epic.legofullstack.fourpaws.network.errorhandle

import java.net.ConnectException
import java.net.ProtocolException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

inline fun <T> safeCall(action: () -> T) : ResponseState<T> =
    try {
        ResponseState.Success(action())
    } catch (throwable : Throwable) {
        ResponseState.Error(throwable.isNetworkException())
    }

inline fun voidSafeCall(action: () -> Void): ResponseState<Unit> =
    try {
        action()
        ResponseState.Success(Unit)
    } catch (throwable : Throwable) {
        ResponseState.Error(throwable.isNetworkException())
    }

fun Throwable.isNetworkException(): Boolean =
    when(this) {
        is ConnectException,
        is SocketException,
        is SocketTimeoutException,
        is UnknownHostException,
        is ProtocolException -> true
        else -> false
    }
