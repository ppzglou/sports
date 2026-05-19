package gr.sppzglou.sports.presentation.screens.base

import gr.sppzglou.sports.domain.getMessage


interface BaseUiState<T : BaseUiData> {
    val data: T?

    val isLoading: Boolean
        get() = data?.inProgress == true

    val isAwaiting: Boolean
        get() = data == null

    val isSuccess: Boolean
        get() = data != null && data!!.error == null && !data!!.inProgress

    val isFailure: Boolean
        get() = data != null && data!!.error != null


    fun getErrorMessage(): String? = data?.error?.getMessage()
}

data class EmptyUiState(
    override val data: EmptyUiData = EmptyUiData(),
) : BaseUiState<EmptyUiData>

