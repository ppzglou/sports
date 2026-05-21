package gr.sppzglou.sports.presentation.screens.splash.vm

import dagger.hilt.android.lifecycle.HiltViewModel
import gr.sppzglou.sports.presentation.screens.base.BaseVM
import gr.sppzglou.sports.presentation.screens.base.EmptyUiData
import gr.sppzglou.sports.presentation.screens.base.EmptyUiState
import kotlinx.coroutines.delay
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
        goToDash()
    }

    private fun goToDash() = launch {
        delay(2000)
        emitEffect(SplashEffect.NavigateToDashboard)
    }
}