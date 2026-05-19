package gr.sppzglou.sports.presentation.screens.splash

import androidx.compose.runtime.Composable
import gr.sppzglou.sports.presentation.screens.splash.vm.SplashVM
import gr.sppzglou.bromance.screens.splash.vm.SplashEffect
import gr.sppzglou.sports.presentation.screens.base.BaseRoute
import gr.sppzglou.sports.presentation.screens.base.on

@Composable
fun SplashRoute(
    vm: SplashVM = SplashVM(),
    goToLandingHost: () -> Unit,
    goToDashboardHost: () -> Unit,
) = BaseRoute(
    vm = vm,
    onEffect = {
        it.on<SplashEffect.NavigateToDash> {
            goToDashboardHost()
        }
        it.on<SplashEffect.NavigateToLogin> {
            goToLandingHost()
        }
    }
) { data ->
    SplashScreen()
}