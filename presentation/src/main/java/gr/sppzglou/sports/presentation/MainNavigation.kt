package gr.sppzglou.sports.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import gr.sppzglou.sports.presentation.screens.splash.SplashRoute
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppHost : AppScreen {

    @Serializable
    data object Splash : AppHost

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

                }
            )
        }
    }
}