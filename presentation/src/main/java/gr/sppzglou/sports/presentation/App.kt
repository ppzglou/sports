package gr.sppzglou.sports.presentation

import android.app.Activity
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import gr.sppzglou.sports.presentation.theme.AppTheme
import gr.sppzglou.sports.presentation.theme.AppThemeProvider

@Composable
fun App() = AppThemeProvider {
    val view = LocalView.current
    val window = (view.context as Activity).window
    val color = AppTheme.colors.background

    SideEffect {
        WindowCompat.getInsetsController(window, view)
            .isAppearanceLightStatusBars = color.luminance() > 0.5f

        WindowCompat.getInsetsController(window, view)
            .isAppearanceLightNavigationBars = color.luminance() > 0.5f

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isNavigationBarContrastEnforced = false
        }
    }

    MainNavigation()
}
