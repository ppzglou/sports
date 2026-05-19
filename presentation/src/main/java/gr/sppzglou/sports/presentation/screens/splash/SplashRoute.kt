package gr.sppzglou.sports.presentation.screens.splash

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import gr.sppzglou.bromance.screens.splash.vm.SplashEffect
import gr.sppzglou.sports.presentation.screens.base.BaseRoute
import gr.sppzglou.sports.presentation.screens.base.on
import gr.sppzglou.sports.presentation.screens.splash.vm.SplashVM

@Composable
fun SplashRoute(
    vm: SplashVM = hiltViewModel(),
    goToDashboard: () -> Unit,
) = BaseRoute(
    vm = vm,
    onEffect = {
        it.on<SplashEffect.NavigateToDash> {
            goToDashboard()
        }
    }
) { data ->
    SplashScreen()
}