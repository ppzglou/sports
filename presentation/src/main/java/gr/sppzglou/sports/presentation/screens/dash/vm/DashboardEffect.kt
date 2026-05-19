package gr.sppzglou.sports.presentation.screens.dash.vm

import gr.sppzglou.sports.presentation.screens.base.BaseEffect

sealed interface DashboardEffect : BaseEffect {
    data object NavigateToFavorites : DashboardEffect
}