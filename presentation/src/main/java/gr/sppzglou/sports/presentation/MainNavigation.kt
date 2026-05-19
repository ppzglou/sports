package gr.sppzglou.sports.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import gr.sppzglou.sports.presentation.screens.dash.DashboardRoute
import gr.sppzglou.sports.presentation.screens.splash.SplashRoute
import gr.sppzglou.sports.presentation.utils.AppNavHost
import gr.sppzglou.sports.presentation.utils.AppScreen
import gr.sppzglou.sports.presentation.utils.replace
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppHost : AppScreen {

    @Serializable
    data object Splash : AppHost

    @Serializable
    data object Dashboard : AppHost

}


@Composable
fun MainNavigation() {
    val nav = rememberNavController()

    AppNavHost(
        navController = nav,
        startDestination = AppHost.Splash
    ) {
        composable<AppHost.Splash> {
            SplashRoute(
                goToDashboard = {
                    nav.replace(AppHost.Dashboard)
                }
            )
        }
        composable<AppHost.Dashboard> {
            DashboardRoute(
                goToFavorites = {

                }
            )
        }
    }
}