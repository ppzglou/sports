package gr.sppzglou.sports.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import gr.sppzglou.sports.presentation.theme.AppTheme


@Immutable
data class AppSwitcherTokens(
    val showStar: Boolean
)

fun switcherDefaultTokens() = AppSwitcherTokens(
    showStar = false
)

fun switcherFavTokens() = AppSwitcherTokens(
    showStar = true
)


@Composable
fun AppSwitcher(
    checked: Boolean,
    modifier: Modifier = Modifier,
    tokens: AppSwitcherTokens = switcherDefaultTokens(),
    onCheckedChange: (Boolean) -> Unit,
) {

    val thumbOffset by animateDpAsState(
        targetValue = if (checked) 20.dp else 0.dp,
        animationSpec = tween(250),
        label = "thumbOffset"
    )

    val backgroundColor by animateColorAsState(
        targetValue =
            if (checked)
                AppTheme.colors.primary
            else
                AppTheme.colors.surfaceVariant,
        animationSpec = tween(250),
        label = "backgroundColor"
    )

    Box(
        modifier = modifier
            .width(48.dp)
            .height(28.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .appClick {
                onCheckedChange(!checked)
            }
            .padding(AppTheme.spacing.xxs)
    ) {

        Box(
            modifier = Modifier
                .offset(x = thumbOffset)
                .size(22.dp)
                .clip(CircleShape)
                .background(AppTheme.colors.background),
            contentAlignment = Alignment.Center
        ) {

            AnimatedVisibility(
                visible = checked && tokens.showStar,
                enter = fadeIn(tween(180)) + scaleIn(tween(180)),
                exit = fadeOut(tween(120)) + scaleOut(tween(120))
            ) {

                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = AppTheme.colors.primary,
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
}