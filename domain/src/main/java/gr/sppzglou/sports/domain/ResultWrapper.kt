package gr.sppzglou.sports.domain

import java.lang.System.currentTimeMillis


sealed class ResultWrapper<out T> {
    data class Success<out T>(
        val data: T,
    ) : ResultWrapper<T>()

    data class Failure(
        val error: FailureWrapper,
    ) : ResultWrapper<Nothing>()

    data object Loading : ResultWrapper<Nothing>()

    data object Awaiting : ResultWrapper<Nothing>()

    companion object {
        fun <T, V> ResultWrapper<T>.dataConverter(block: (T) -> V): ResultWrapper<V> =
            when (this) {
                is Success -> Success(block(data))
                is Failure -> Failure(error)
                is Loading -> Loading
                is Awaiting -> Awaiting
            }
    }

    val isSuccess: Boolean
        get() = this is Success

    val isFailure: Boolean
        get() = this is Failure

    val successData: T
        get() = (this as Success).data

    val safeSuccessData: T?
        get() = (this as? Success)?.data

    val failureError: FailureWrapper
        get() = (this as Failure).error

    val safeFailureError: FailureWrapper?
        get() = (this as? Failure)?.error

    val failure: Failure
        get() = this as Failure

    val safeFailure: Failure?
        get() = this as? Failure

}

fun <T> errorMessage(error: String): ResultWrapper<T> =
    ResultWrapper.Failure(FailureWrapper.Message(error))

fun <T> inProgress(): ResultWrapper<T> = ResultWrapper.Loading

fun <T> success(data: T): ResultWrapper<T> = ResultWrapper.Success(data)

fun successUnit() = success(Unit)

sealed class FailureWrapper {

    data class Code(
        val errorCode: ErrorCodes,
        val id: Long = currentTimeMillis()
    ) : FailureWrapper()

    data class Message(
        val errorMessage: String,
        val id: Long = currentTimeMillis()
    ) : FailureWrapper()
}

fun FailureWrapper?.getMessage(): String? = when (this) {
    is FailureWrapper.Message -> this.errorMessage
    is FailureWrapper.Code -> this.errorCode.name
    else -> null
}

enum class ErrorCodes {
    NoInternet,
    Unauthorized,
}