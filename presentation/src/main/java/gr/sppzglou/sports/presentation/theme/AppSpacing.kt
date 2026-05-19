package gr.sppzglou.sports.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val LocalAppSpacing =
    staticCompositionLocalOf<AppSpacing> {
        error("AppSpacing not provided")
    }

@Immutable
data class AppSpacing(
    val none: Dp,
    val xxs: Dp,
    val xs: Dp,
    val xsPlus: Dp,
    val sm: Dp,
    val md: Dp,
    val mdPlus: Dp,
    val lg: Dp,
    val lgPlus: Dp,
    val xl: Dp,
    val xxl: Dp,
    val xxxl: Dp,
)

val DefaultAppSpacing = AppSpacing(
    none = 0.dp,
    xxs = 2.dp,
    xs = 4.dp,
    xsPlus = 6.dp,
    sm = 8.dp,
    md = 10.dp,
    mdPlus = 12.dp,
    lg = 16.dp,
    lgPlus = 20.dp,
    xl = 24.dp,
    xxl = 32.dp,
    xxxl = 40.dp
)
