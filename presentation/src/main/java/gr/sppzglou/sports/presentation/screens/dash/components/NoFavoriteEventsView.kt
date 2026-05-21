package gr.sppzglou.sports.presentation.screens.dash.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import gr.sppzglou.sports.presentation.components.AppText
import gr.sppzglou.sports.presentation.theme.AppTheme

@Composable
fun NoFavoriteEventsView(sportName: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(AppTheme.colors.surface)
            .border(
                width = 1.dp,
                color = AppTheme.colors.divider,
                shape = RoundedCornerShape(18.dp)
            )
            .padding(
                horizontal = 20.dp,
                vertical = 28.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.StarBorder,
            contentDescription = null,
            tint = AppTheme.colors.favorite,
            modifier = Modifier.size(42.dp)
        )

        Spacer(Modifier.height(AppTheme.spacing.md))

        AppText(
            text = "No favourite events",
            style = AppTheme.typography.h4.copy(
                color = AppTheme.colors.textPrimary
            ),
        )

        Spacer(Modifier.height(AppTheme.spacing.xs))

        AppText(
            text = "There are no favourite events for $sportName yet",
            style = AppTheme.typography.caption.copy(
                color = AppTheme.colors.textSecondary
            ),
        )
    }
}