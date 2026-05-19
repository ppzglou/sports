package gr.sppzglou.bromance.screens.splash.vm

import gr.sppzglou.sports.presentation.screens.base.BaseEffect

sealed interface SplashEffect : BaseEffect {
    data object NavigateToDash : SplashEffect
    data object NavigateToLogin : SplashEffect
}