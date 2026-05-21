package gr.sppzglou.sports.presentation.screens.dash.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gr.sppzglou.sports.domain.models.SportDomain
import gr.sppzglou.sports.presentation.R
import gr.sppzglou.sports.presentation.components.AppSwitcher
import gr.sppzglou.sports.presentation.components.AppText
import gr.sppzglou.sports.presentation.components.appClick
import gr.sppzglou.sports.presentation.components.switcherFavTokens
import gr.sppzglou.sports.presentation.theme.AppTheme
import gr.sppzglou.sports.presentation.theme.AppThemeProvider
import gr.sppzglou.sports.presentation.utils.LettersFormat
import gr.sppzglou.sports.presentation.utils.textFormat

@Composable
fun SportListItem(
    sport: SportDomain,
    isExpanded: Boolean = false,
    isFavorite: Boolean,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    val title = textFormat(
        sport.name,
        LettersFormat.Uppercase
    )

    Box {
        Box(Modifier.matchParentSize()) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .background(AppTheme.colors.background)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(AppTheme.shapes.xlPlus)
                .background(AppTheme.colors.surface)
                .border(
                    width = 1.dp,
                    color = AppTheme.colors.divider,
                    shape = AppTheme.shapes.xlPlus
                )
                .appClick {
                    onClick()
                }
                .padding(
                    horizontal = AppTheme.spacing.lg,
                    vertical = AppTheme.spacing.md,
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(
                        AppTheme.colors.primary.copy(alpha = 0.2f)
                    ),
                contentAlignment = Alignment.Center
            ) {

                title.iconRes()?.let {

                    Image(
                        painter = painterResource(it),
                        contentDescription = sport.name,
                        modifier = Modifier
                            .fillMaxSize(0.6f)
                            .clip(CircleShape)
                    )

                } ?: Box(
                    Modifier
                        .fillMaxSize(0.6f)
                        .clip(CircleShape)
                        .background(AppTheme.colors.primary)
                )
            }

            Spacer(Modifier.width(AppTheme.spacing.md))

            AppText(
                text = title,
                style = AppTheme.typography.h5,
                modifier = Modifier.weight(1f)
            )

            AppSwitcher(
                isFavorite,
                Modifier.padding(horizontal = AppTheme.spacing.md),
                switcherFavTokens()
            ) {
                onFavoriteClick()
            }

            Icon(
                imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp
                else Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = AppTheme.colors.textSecondary,
                modifier = Modifier.size(22.dp)
            )
        }
    }
}

fun String.iconRes(): Int? {
    return when (uppercase()) {
        "SOCCER" -> R.mipmap.soccer
        "BASKETBALL" -> R.mipmap.basketball
        "TENNIS" -> R.mipmap.tennis
        "TABLETENNIS" -> R.mipmap.tabletennis
        "VOLLEYBALL" -> R.mipmap.volleyball
        "ESPORTS" -> R.mipmap.esports
        "ICEHOCKEY" -> R.mipmap.icehockey
        "HANDBALL" -> R.mipmap.handball
        "SNOOKER" -> R.mipmap.snooker
        "FUTSAL" -> R.mipmap.futsal
        "DARTS" -> R.mipmap.darts
        else -> null
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
        SportListItem(
            sport = SportDomain(
                id = "",
                name = "SOCCER",
                events = listOf()
            ),
            isFavorite = true,
            onClick = {}
        ) {}
    }
}