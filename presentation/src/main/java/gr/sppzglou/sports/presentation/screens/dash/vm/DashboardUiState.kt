package gr.sppzglou.sports.presentation.screens.dash.vm

import gr.sppzglou.sports.domain.ResultWrapper
import gr.sppzglou.sports.domain.inProgress
import gr.sppzglou.sports.domain.models.EventDomain
import gr.sppzglou.sports.domain.models.SportDomain
import gr.sppzglou.sports.presentation.screens.base.BaseUiData
import gr.sppzglou.sports.presentation.screens.base.BaseUiState
import gr.sppzglou.sports.presentation.theme.AppTheme

data class DashboardUiState(
    override val result: ResultWrapper<DashboardUiData> = inProgress(),
) : BaseUiState<DashboardUiData>

data class DashboardUiData(
    val items: List<DashboardListItem> = listOf(),
    val extendedItems: List<String> = listOf(),
    val sportFavIds: List<String> = listOf(),
    val currentTime: Long = 0L,
    val theme: AppTheme.Mode = AppTheme.Mode.System,
    val favCount: Int = 0
) : BaseUiData


sealed interface DashboardListItem {

    data class Sport(
        val sport: SportDomain,
    ) : DashboardListItem

    data class Event(
        val sportName: String,
        val event: EventDomain
    ) : DashboardListItem
}