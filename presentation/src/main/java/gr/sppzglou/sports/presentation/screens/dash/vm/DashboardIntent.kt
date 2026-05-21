package gr.sppzglou.sports.presentation.screens.dash.vm

sealed interface DashboardIntent {
    data class SportClicked(val sport: String) : DashboardIntent

    data object ThemeClicked : DashboardIntent

    data class FavoriteClicked(val id: String) : DashboardIntent

    data class SportFavoriteClicked(val id: String) : DashboardIntent

    data object NavigateToFavorites : DashboardIntent

}
