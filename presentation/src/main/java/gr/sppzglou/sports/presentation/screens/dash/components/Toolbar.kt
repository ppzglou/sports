package gr.sppzglou.sports.presentation.screens.dash.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoMode
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gr.sppzglou.sports.presentation.R
import gr.sppzglou.sports.presentation.components.AppText
import gr.sppzglou.sports.presentation.components.appClick
import gr.sppzglou.sports.presentation.theme.AppTheme
import gr.sppzglou.sports.presentation.theme.AppThemeProvider
import gr.sppzglou.sports.presentation.utils.str

@Composable
fun Toolbar(
    username: String = "Symeon",
    favoriteCount: Int = 12,
    theme: AppTheme.Mode = AppTheme.Mode.System,
    onThemeClick: () -> Unit = {},
    onFavoritesClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = AppTheme.spacing.lg,
                vertical = AppTheme.spacing.md
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.linearGradient(
                            listOf(
                                AppTheme.colors.primary,
                                AppTheme.colors.primaryDark
                            )
                        )
                    )
                    .appClick {
                        onProfileClick()
                    },
                contentAlignment = Alignment.Center
            ) {
                AppText(
                    text = username.take(1),
                    style = AppTheme.typography.h3.copy(
                        Color.White
                    )
                )
            }

            Column(
                modifier = Modifier.padding(start = AppTheme.spacing.md)
            ) {

                AppText(
                    text = str(R.string.welcome_back),
                    style = AppTheme.typography.caption.copy(
                        AppTheme.colors.textSecondary
                    )
                )

                Spacer(Modifier.height(2.dp))

                AppText(
                    text = username,
                    style = AppTheme.typography.h2
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.sm)
        ) {

            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
                    .background(AppTheme.colors.surface)
                    .border(
                        width = 1.dp,
                        color = AppTheme.colors.divider,
                        shape = CircleShape
                    )
                    .appClick {
                        onThemeClick()
                    },
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = when (theme) {
                        AppTheme.Mode.Dark -> Icons.Default.DarkMode
                        AppTheme.Mode.Light -> Icons.Default.LightMode
                        AppTheme.Mode.System -> Icons.Default.AutoMode
                    },
                    contentDescription = null,
                    tint = AppTheme.colors.primary,
                    modifier = Modifier.size(22.dp)
                )
            }

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(AppTheme.colors.surface)
                    .border(
                        width = 1.dp,
                        color = AppTheme.colors.divider,
                        shape = CircleShape
                    )
                    .appClick {
                        onFavoritesClick()
                    }
                    .padding(
                        horizontal = 14.dp,
                        vertical = 10.dp
                    )
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = AppTheme.colors.favorite,
                        modifier = Modifier.size(18.dp)
                    )

                    AppText(
                        text = favoriteCount.toString(),
                        style = AppTheme.typography.bodyBold
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewItem() {
    AppThemeProvider(
        themeMode = AppTheme.Mode.Dark,
        maxSize = false,
        showBG = false
    ) {
        Toolbar()
    }
}