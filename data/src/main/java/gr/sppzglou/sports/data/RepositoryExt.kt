package gr.sppzglou.sports.data

import gr.sppzglou.sports.domain.ResultWrapper
import gr.sppzglou.sports.domain.errorMessage
import gr.sppzglou.sports.domain.success

open class RepositoryExt {

    suspend fun <T> ResultWrapper<T>.onSuccessUnit(
        action: suspend (T) -> Unit = {}
    ): ResultWrapper<Unit> =
        onSuccessTransform {
            action(it)
            Unit
        }

    suspend fun <T, V> ResultWrapper<T>.onSuccessTransform(action: suspend (T) -> V): ResultWrapper<V> {
        return if (isSuccess) {
            try {
                success(action(successData))
            } catch (t: Throwable) {
                errorMessage(t.message ?: t.toString())
            }
        } else {
            failure
        }
    }

}