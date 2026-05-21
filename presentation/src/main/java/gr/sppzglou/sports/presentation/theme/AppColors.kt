package gr.sppzglou.sports.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalAppColors =
    staticCompositionLocalOf<AppColors> {
        error("AppColors not provided")
    }

@Immutable
data class AppColors(

    // Backgrounds
    val background: Color,
    val surface: Color,
    val surfaceVariant: Color,

    // Brand
    val primary: Color,
    val primaryDark: Color,
    val secondary: Color,

    // Text
    val textPrimary: Color,
    val textSecondary: Color,
    val textPlaceholder: Color,

    // States
    val divider: Color,
    val error: Color,

    // Sports extras
    val favorite: Color,
    val countdown: Color,
)

val LightColors = AppColors(

    // Backgrounds
    background = Color(0xFFF5F7FA),
    surface = Color(0xFFFFFFFF),
    surfaceVariant = Color(0xFFE9EEF5),

    // Brand
    primary = Color(0xFF00A86B),
    primaryDark = Color(0xFF007A4D),
    secondary = Color(0xFFFFB300),

    // Text
    textPrimary = Color(0xFF101828),
    textSecondary = Color(0xFF667085),
    textPlaceholder = Color(0xFF98A2B3),

    // States
    divider = Color(0xFFD0D5DD),
    error = Color(0xFFD92D20),

    // Sports extras
    favorite = Color(0xFFFFC107),
    countdown = Color(0xFF027A48)
)

val DarkColors = AppColors(

    // Backgrounds
    background = Color(0xFF07130E),
    surface = Color(0xFF102019),
    surfaceVariant = Color(0xFF182D23),

    // Brand
    primary = Color(0xFF00C781),
    primaryDark = Color(0xFF009B63),
    secondary = Color(0xFFFFC857),

    // Text
    textPrimary = Color(0xFFF2F4F7),
    textSecondary = Color(0xFFB7C0BE),
    textPlaceholder = Color(0xFF7A8B86),

    // States
    divider = Color(0xFF28463A),
    error = Color(0xFFFF6B6B),

    // Sports extras
    favorite = Color(0xFFFFD166),
    countdown = Color(0xFF5DFFB0)
)