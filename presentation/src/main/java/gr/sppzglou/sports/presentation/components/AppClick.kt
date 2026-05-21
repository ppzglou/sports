package gr.sppzglou.sports.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import gr.sppzglou.sports.presentation.theme.AppTheme

@Composable
fun Modifier.appClick(
    ripple: Color = AppTheme.colors.primary,
    interaction: MutableInteractionSource = remember { MutableInteractionSource() },
    bounded: Boolean = true,
    click: () -> Unit,
): Modifier =
    this.clickable(
        interactionSource = interaction,
        indication = ripple(bounded = bounded, color = ripple),
        onClick = {
            click()
        },
    )
