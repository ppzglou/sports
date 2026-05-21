package gr.sppzglou.sports.presentation.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp


val LocalAppShapes =
    staticCompositionLocalOf<AppShapes> {
        error("AppShapes not provided")
    }

@Immutable
data class AppShapes(
    val sm: RoundedCornerShape,
    val md: RoundedCornerShape,
    val lg: RoundedCornerShape,
    val xl: RoundedCornerShape,
    val xlPlus: RoundedCornerShape,
    val xxl: RoundedCornerShape,
)

val DefaultAppShapes = AppShapes(
    sm = RoundedCornerShape(6.dp),
    md = RoundedCornerShape(8.dp),
    lg = RoundedCornerShape(12.dp),
    xl = RoundedCornerShape(16.dp),
    xlPlus = RoundedCornerShape(22.dp),
    xxl = RoundedCornerShape(24.dp),
)
