package gr.sppzglou.sports.presentation.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.datastore.preferences.core.stringPreferencesKey
import gr.sppzglou.sports.presentation.utils.collect
import gr.sppzglou.sports.presentation.utils.rememberDataStore

@Composable
fun AppThemeProvider(
    themeMode: AppTheme.Mode? = null,
    content: @Composable () -> Unit
) {
    val isDarkTheme = when (themeMode) {
        AppTheme.Mode.Dark -> true
        AppTheme.Mode.Light -> false
        AppTheme.Mode.System -> isSystemInDarkTheme()
        else -> null
    }

    val colors = if (isDarkTheme ?: isDarkTheme()) DarkColors else LightColors
    val typography = provideAppTypography(colors)

    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalAppTypography provides typography,
        LocalAppSpacing provides DefaultAppSpacing,
        LocalAppShapes provides DefaultAppShapes,
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(colors.background)
        ) {
            content()
        }
    }
}

@Composable
fun getThemeMode(): State<AppTheme.Mode> =
    rememberDataStore().collect(AppTheme.dataStoreKey, AppTheme.Mode.System.name) {
        AppTheme.Mode.valueOf(it)
    }

@Composable
fun isDarkTheme(): Boolean {
    val theme by getThemeMode()
    val isSystemDark = isSystemInDarkTheme()
    val isDark =
        remember(theme, isSystemDark) {
            theme == AppTheme.Mode.Dark || (theme == AppTheme.Mode.System && isSystemDark)
        }

    return isDark
}

object AppTheme {
    val dataStoreKey = stringPreferencesKey("theme")

    enum class Mode {
        Dark,
        Light,
        System,
    }

    val colors: AppColors
        @Composable get() = LocalAppColors.current

    val typography: AppTypography
        @Composable get() = LocalAppTypography.current

    val spacing: AppSpacing
        @Composable get() = LocalAppSpacing.current

    val shapes: AppShapes
        @Composable get() = LocalAppShapes.current
}

@Composable
fun provideAppTypography(
    colors: AppColors
): AppTypography =
    AppTypography(

        h1 = BaseAppTypography.h1.copy(
            color = colors.textPrimary
        ),
        h2 = BaseAppTypography.h2.copy(
            color = colors.textPrimary
        ),
        h3 = BaseAppTypography.h3.copy(
            color = colors.textPrimary
        ),
        h4 = BaseAppTypography.h4.copy(
            color = colors.textPrimary
        ),
        h5 = BaseAppTypography.h5.copy(
            color = colors.textPrimary
        ),
        body = BaseAppTypography.body.copy(
            color = colors.textPrimary
        ),
        bodyBold = BaseAppTypography.bodyBold.copy(
            color = colors.textPrimary
        ),
        caption = BaseAppTypography.caption.copy(
            color = colors.textSecondary
        ),
        button = BaseAppTypography.button.copy(
            color = colors.background
        )
    )
