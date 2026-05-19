package gr.sppzglou.sports.presentation.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

const val THEME_MODE = "THEME_MODE"

enum class ThemeMode {
    Dark,
    Light,
    System,
}

@Composable
fun AppThemeProvider(
    content: @Composable () -> Unit
) {

    val colors = if (isDarkTheme()) DarkAppColors else LightAppColors
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

//@Composable
//fun getThemeMode(): State<ThemeMode> =
//    rememberDataStore().collect(THEME_MODE, ThemeMode.System.ordinal) {
//        ThemeMode.entries[it]
//    }

@Composable
fun isDarkTheme(): Boolean {
    val theme = ThemeMode.System///by getThemeMode()
    val isSystemDark = isSystemInDarkTheme()
    val isDark =
        remember(theme, isSystemDark) {
            theme == ThemeMode.Dark || (theme == ThemeMode.System && isSystemDark)
        }

    return isDark
}

object AppTheme {

    val colors: AppDynamicColors
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
    colors: AppDynamicColors
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
            color = colors.textSecondary
        ),
        caption = BaseAppTypography.caption.copy(
            color = colors.textPlaceholder
        ),
        button = BaseAppTypography.button.copy(
            color = colors.background
        )
    )
