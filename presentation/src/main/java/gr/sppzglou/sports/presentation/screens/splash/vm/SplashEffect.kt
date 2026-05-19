package gr.sppzglou.sports.presentation.screens.splash.vm

import gr.sppzglou.sports.presentation.screens.base.BaseEffect

sealed interface SplashEffect : BaseEffect {
    data object NavigateToDashboard : SplashEffect
}