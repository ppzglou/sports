package gr.sppzglou.sports.presentation.screens.dash

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import gr.sppzglou.sports.presentation.screens.base.BaseRoute
import gr.sppzglou.sports.presentation.screens.base.on
import gr.sppzglou.sports.presentation.screens.dash.vm.DashboardEffect
import gr.sppzglou.sports.presentation.screens.dash.vm.DashboardVM

@Composable
fun DashboardRoute(
    vm: DashboardVM = hiltViewModel(),
    goToFavorites: () -> Unit,
) = BaseRoute(
    vm = vm,
    onEffect = {
        it.on<DashboardEffect.NavigateToFavorites> {
            goToFavorites()
        }
    }
) { data ->
    DashboardScreen(
        data,
        vm::onIntent
    )
}