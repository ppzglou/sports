package gr.sppzglou.sports.presentation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import kotlin.jvm.JvmSuppressWildcards

interface AppScreen


@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: AppScreen,
    anim: NavAnim = NavAnim(),
    builder: NavGraphBuilder.() -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = anim.enter,
        exitTransition = anim.exit,
        popEnterTransition = anim.popEnter,
        popExitTransition = anim.popExit,
        builder = builder
    )
}

fun NavHostController.replace(host: AppScreen) {
    navigate(host) {
        popUpTo(currentBackStackEntry!!.destination.id) {
            inclusive = true
        }
    }
}

data class NavAnim(
    val enter: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = {
        slideInHorizontally(animationSpec = tween(300)) { full -> full }
    },
    val exit: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = {
        slideOutHorizontally(animationSpec = tween(300)) { full -> -full }
    },
    val popEnter: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = {
        slideInHorizontally(animationSpec = tween(300)) { full -> -full }
    },
    val popExit: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = {
        slideOutHorizontally(animationSpec = tween(300)) { full -> full }
    }
)

val FadeNavAnim = NavAnim(
    enter = {
        fadeIn(
            animationSpec = tween(200),
            initialAlpha = 0f
        )
    },
    exit = {
        fadeOut(
            animationSpec = tween(200),
            targetAlpha = 0f
        )
    },
    popEnter = {
        fadeIn(
            animationSpec = tween(200),
            initialAlpha = 0f
        )
    },
    popExit = {
        fadeOut(
            animationSpec = tween(200),
            targetAlpha = 0f
        )
    }
)

fun NavController.navigateSingleTop(route: AppScreen) {
    navigate(route) {
        launchSingleTop = true
        restoreState = true

        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
    }
}