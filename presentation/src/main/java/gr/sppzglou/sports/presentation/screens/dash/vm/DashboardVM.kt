package gr.sppzglou.sports.presentation.screens.dash.vm

import androidx.datastore.preferences.core.edit
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gr.sppzglou.sports.domain.cases.ChangeFavoritesUC
import gr.sppzglou.sports.domain.cases.FetchDataUC
import gr.sppzglou.sports.domain.cases.GetSportsFlowUC
import gr.sppzglou.sports.domain.models.SportDomain
import gr.sppzglou.sports.domain.success
import gr.sppzglou.sports.presentation.screens.base.BaseVM
import gr.sppzglou.sports.presentation.theme.AppTheme
import gr.sppzglou.sports.presentation.utils.PrefsDataStore
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.lang.System.currentTimeMillis
import javax.inject.Inject

@HiltViewModel
class DashboardVM @Inject constructor(
    private val dataStore: PrefsDataStore,
    private val fetchDataUC: FetchDataUC,
    private val getSportsFlowUC: GetSportsFlowUC,
    private val changeFavoritesUC: ChangeFavoritesUC
) : BaseVM<
        DashboardUiState,
        DashboardUiData,
        DashboardEffect
        >(
    initialState = DashboardUiState()
) {
    private var timerJob: Job? = null

    init {
        observeTheme()
        observeSports()
    }

    fun onIntent(intent: DashboardIntent) {
        when (intent) {
            is DashboardIntent.SportClicked -> onSportClicked(intent)
            is DashboardIntent.ThemeClicked -> onThemeClicked()
            is DashboardIntent.FavoriteClicked -> onFavoriteClicked(intent)
        }
    }

    private fun fetchData() = launch {
        delay(5000)
        val res = fetchDataUC()
        if (res.isFailure) {
            updateData {
                copy(items = items)
            }
        }
    }

    private fun observeSports() = launch {
        getSportsFlowUC().collect { sports ->
            if (sports.isEmpty()) {
                fetchData()
            } else if (getDataOrNull() == null) {
                setState { state ->
                    state.copy(
                        result = success(
                            DashboardUiData(
                                items = flattenSportsEventsList(sports),
                                favCount = getFavoriteCounter(sports)
                            )
                        )
                    )
                }
                if (timerJob?.isActive != true) {
                    startTimer()
                }
            } else {
                updateData {
                    copy(
                        items = flattenSportsEventsList(sports),
                        favCount = getFavoriteCounter(sports)
                    )
                }
                if (timerJob?.isActive != true) {
                    startTimer()
                }
            }
        }
    }

    private fun getFavoriteCounter(sports: List<SportDomain>) =
        sports.flatMap { it.events }.filter { it.isFav }.size

    private fun observeTheme() = launch {
        dataStore.data.collect { preferences ->
            val theme = runCatching {
                AppTheme.Mode.valueOf(
                    preferences[AppTheme.dataStoreKey] ?: AppTheme.Mode.System.name
                )
            }.getOrDefault(AppTheme.Mode.System)

            updateData {
                copy(theme = theme)
            }
        }
    }

    private fun onThemeClicked() = launch {
        val modes = AppTheme.Mode.entries
        val preferences = dataStore.data.first()
        val current = runCatching {
            AppTheme.Mode.valueOf(
                preferences[AppTheme.dataStoreKey]
                    ?: AppTheme.Mode.System.name
            )
        }.getOrDefault(AppTheme.Mode.System)

        val next = modes[(current.ordinal + 1) % modes.size]

        dataStore.edit { preferences ->
            preferences[AppTheme.dataStoreKey] = next.name
        }
    }

    private fun onFavoriteClicked(intent: DashboardIntent.FavoriteClicked) = launch {
        changeFavoritesUC(intent.ids, intent.flag)
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (true) {
                updateData {
                    copy(currentTime = currentTimeMillis())
                }
                delay(1000)
            }
        }
    }

    private fun onSportClicked(intent: DashboardIntent.SportClicked) {
        updateData {
            val list =
                if (extendedItems.contains(intent.sport)) extendedItems - intent.sport
                else extendedItems + intent.sport

            copy(extendedItems = list)
        }
    }

    private fun flattenSportsEventsList(sports: List<SportDomain>) = buildList {
        sports.forEach { sport ->
            add(DashboardListItem.Sport(sport, !sport.events.any { !it.isFav }))

            sport.events.forEach { event ->
                add(
                    DashboardListItem.Event(
                        sportName = sport.name,
                        event = event
                    )
                )
            }
        }
    }

    private fun updateData(
        transform: DashboardUiData.() -> DashboardUiData
    ) {
        updateData(
            transform = transform,
            stateMapper = { state, newResult ->
                state.copy(result = newResult)
            }
        )
    }
}