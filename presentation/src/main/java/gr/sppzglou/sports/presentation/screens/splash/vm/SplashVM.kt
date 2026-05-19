package gr.sppzglou.sports.presentation.screens.splash.vm

import dagger.hilt.android.lifecycle.HiltViewModel
import gr.sppzglou.bromance.screens.splash.vm.SplashEffect
import gr.sppzglou.sports.domain.FailureWrapper
import gr.sppzglou.sports.presentation.screens.base.BaseVM
import gr.sppzglou.sports.presentation.screens.base.EmptyUiData
import gr.sppzglou.sports.presentation.screens.base.EmptyUiState
import javax.inject.Inject

@HiltViewModel
class SplashVM @Inject constructor(
) : BaseVM<
        EmptyUiState,
        EmptyUiData,
        SplashEffect
        >(
    initialState = EmptyUiState()
) {

    init {
        isLongedIn()
    }

    private fun isLongedIn() = launch {

    }

    override fun updateData(transform: EmptyUiData.() -> EmptyUiData) = Unit
    override fun setError(error: FailureWrapper) = Unit
    override fun switchLoading(bool: Boolean) = Unit
}