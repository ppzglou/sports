package gr.sppzglou.sports.presentation.screens.dash.vm

import dagger.hilt.android.lifecycle.HiltViewModel
import gr.sppzglou.sports.domain.FailureWrapper
import gr.sppzglou.sports.presentation.screens.base.BaseVM
import gr.sppzglou.sports.presentation.screens.base.EmptyUiData
import gr.sppzglou.sports.presentation.screens.base.EmptyUiState
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class DashboardVM @Inject constructor(
) : BaseVM<
        EmptyUiState,
        EmptyUiData,
        DashboardEffect
        >(
    initialState = EmptyUiState()
) {

    init {
        goToDash()
    }

    private fun goToDash() = launch {
        delay(2000)
        emitEffect(DashboardEffect.NavigateToFavorites)
    }

    override fun updateData(transform: EmptyUiData.() -> EmptyUiData) = Unit
    override fun setError(error: FailureWrapper) = Unit
    override fun switchLoading(bool: Boolean) = Unit
}