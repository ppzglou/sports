package gr.sppzglou.sports.presentation.screens.base

import gr.sppzglou.sports.domain.FailureWrapper
import gr.sppzglou.sports.domain.ResultWrapper


interface BaseUiState<T : BaseUiData> {
    val result: ResultWrapper<T>

    val isLoading: Boolean
        get() = result is ResultWrapper.Loading

    val isAwaiting: Boolean
        get() = result is ResultWrapper.Awaiting

    val isSuccess: Boolean
        get() = result is ResultWrapper.Success

    val isFailure: Boolean
        get() = result is ResultWrapper.Failure

    fun getDataOrNull(): T? = (result as? ResultWrapper.Success)?.data

    fun getErrorOrNull(): FailureWrapper? = (result as? ResultWrapper.Failure)?.error

    fun getErrorMessage(): String? =
        when (val err = getErrorOrNull()) {
            is FailureWrapper.Message -> err.errorMessage
            is FailureWrapper.Code -> err.errorCode.name
            else -> null
        }
}

data class EmptyUiState(
    override val result: ResultWrapper<EmptyUiData> = ResultWrapper.Loading,
) : BaseUiState<EmptyUiData>

