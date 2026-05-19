package gr.sppzglou.sports.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalAppColors =
    staticCompositionLocalOf<AppDynamicColors> {
        error("AppColors not provided")
    }

@Immutable
data class AppDynamicColors(

    // Background
    val background: Color,

    // Text
    val textPrimary: Color,
    val textSecondary: Color,
    val textPlaceholder: Color,

)

val LightAppColors = AppDynamicColors(

    background = Color(0xFFFFFFFF),

    textPrimary = Color(0xFF0A0A0A),
    textSecondary = Color(0xFF404040),
    textPlaceholder = Color(0xFFA7A7AC),
)

val DarkAppColors = AppDynamicColors(

    background = Color(0xFF121E34),

    textPrimary = Color(0xFFFFFFFF),
    textSecondary = Color(0xFFC6C6C6),
    textPlaceholder = Color(0xFFDEDEDE),
)