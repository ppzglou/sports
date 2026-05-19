package gr.sppzglou.sports.data.remote

import gr.sppzglou.sports.domain.FailureWrapper
import gr.sppzglou.sports.domain.ResultWrapper
import retrofit2.Response

suspend inline fun <reified T> RemoteDataSource.safeCall(
    call: suspend () -> Response<T>
): ResultWrapper<T> {
    return try {
        val response = call()

        if (response.isSuccessful) {
            val body = response.body()

            if (body != null) {
                ResultWrapper.Success(body)
            } else if (T::class == Unit::class) {
                @Suppress("UNCHECKED_CAST")
                ResultWrapper.Success(Unit as T)
            } else {
                ResultWrapper.Failure(
                    FailureWrapper.Message("Empty response body")
                )
            }
        } else {
            ResultWrapper.Failure(
                FailureWrapper.Message(
                    response.errorBody()?.string() ?: response.message()
                )
            )
        }
    } catch (e: Exception) {
        ResultWrapper.Failure(
            FailureWrapper.Message(e.message ?: "Network error")
        )
    }
}