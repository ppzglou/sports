package gr.sppzglou.sports.presentation.screens.dash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import gr.sppzglou.sports.domain.ResultWrapper
import gr.sppzglou.sports.presentation.components.ShimmerEffect
import gr.sppzglou.sports.presentation.screens.dash.components.EventListItem
import gr.sppzglou.sports.presentation.screens.dash.components.NoFavoriteEventsView
import gr.sppzglou.sports.presentation.screens.dash.components.SportListItem
import gr.sppzglou.sports.presentation.screens.dash.components.Toolbar
import gr.sppzglou.sports.presentation.screens.dash.vm.DashboardIntent
import gr.sppzglou.sports.presentation.screens.dash.vm.DashboardListItem
import gr.sppzglou.sports.presentation.screens.dash.vm.DashboardUiData
import gr.sppzglou.sports.presentation.theme.AppTheme

@Composable
fun DashboardScreen(
    state: ResultWrapper<DashboardUiData>,
    onIntent: (DashboardIntent) -> Unit
) {
    val theme = if (!state.isSuccess) AppTheme.Mode.System else state.successData.theme
    val favCounter = if (!state.isSuccess) 0 else state.successData.favCount

    val navBarPadding = WindowInsets.navigationBars
        .asPaddingValues()
        .calculateBottomPadding()

    Column(
        Modifier
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        Toolbar(
            theme = theme,
            favoriteCount = favCounter,
            onThemeClick = {
                onIntent(DashboardIntent.ThemeClicked)
            },
            onFavoritesClick = {
                onIntent(DashboardIntent.FavoritesClicked)
            }
        )

        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(horizontal = AppTheme.spacing.lgPlus),
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.md),
            contentPadding = PaddingValues(bottom = navBarPadding + AppTheme.spacing.lgPlus)
        ) {
            if (state.inProgress) {
                items(10) {
                    ShimmerEffect(
                        alpha = 0.5f,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .clip(AppTheme.shapes.xxl)
                    )
                }
            }
            if (state.isSuccess) {
                val data = state.successData

                data.items.forEach { item ->
                    when (item) {
                        is DashboardListItem.Sport -> {
                            stickyHeader(
                                key = "sport_${item.sport.id}"
                            ) {
                                SportListItem(
                                    sport = item.sport,
                                    isExpanded = data.extendedItems.contains(item.sport.name),
                                    data.sportFavIds.contains(item.sport.id),
                                    onClick = {
                                        onIntent(DashboardIntent.SportClicked(item.sport.name))
                                    },
                                    onFavoriteClick = {
                                        onIntent(
                                            DashboardIntent.SportFavoriteClicked(item.sport.id)
                                        )
                                    },
                                )
                            }
                            if (item.sport.events.isEmpty()) {
                                item {
                                    NoFavoriteEventsView(item.sport.name)
                                }
                            }
                        }

                        is DashboardListItem.Event -> {
                            if (data.extendedItems.contains(item.sportName)) {
                                item(
                                    key = "event_${item.event.id}"
                                ) {
                                    EventListItem(
                                        event = item.event,
                                        sport = item.sportName,
                                        nowMillis = data.currentTime,
                                        onFavoriteClick = {
                                            onIntent(
                                                DashboardIntent.FavoriteClicked(item.event.id)
                                            )
                                        },
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}