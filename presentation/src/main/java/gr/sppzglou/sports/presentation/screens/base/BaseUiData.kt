package gr.sppzglou.sports.presentation.screens.base

import gr.sppzglou.sports.domain.FailureWrapper


interface BaseUiData {
    val error: FailureWrapper?
    val inProgress: Boolean
}

data class EmptyUiData(
    override val error: FailureWrapper? = null,
    override val inProgress: Boolean = false,
) : BaseUiData
