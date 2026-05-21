package gr.sppzglou.sports.presentation.screens.dash.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gr.sppzglou.sports.domain.models.EventDomain
import gr.sppzglou.sports.presentation.R
import gr.sppzglou.sports.presentation.components.AppText
import gr.sppzglou.sports.presentation.components.appClick
import gr.sppzglou.sports.presentation.theme.AppTheme
import gr.sppzglou.sports.presentation.theme.AppThemeProvider
import gr.sppzglou.sports.presentation.utils.LettersFormat
import gr.sppzglou.sports.presentation.utils.str
import gr.sppzglou.sports.presentation.utils.textFormat
import java.util.concurrent.TimeUnit

@Composable
fun EventListItem(
    event: EventDomain,
    sport: String,
    nowMillis: Long,
    onFavoriteClick: (String) -> Unit,
) {
    val eventTimeMillis = TimeUnit.SECONDS.toMillis(event.time)
    val remainingMillis = (eventTimeMillis - nowMillis).coerceAtLeast(0L)
    val hasExpired = remainingMillis == 0L

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
            .padding(14.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppText(
                text = event.competitor1,
                style = AppTheme.typography.body,
                modifier = Modifier.weight(1f),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AppText(
                    text = str(R.string.vs),
                    style = AppTheme.typography.caption.copy(
                        color = AppTheme.colors.textPlaceholder,
                    ),
                    modifier = Modifier.padding(horizontal = 10.dp)
                )

                textFormat(sport, LettersFormat.Uppercase).iconRes()?.let {
                    Image(
                        painter = painterResource(it),
                        contentDescription = sport,
                        modifier = Modifier
                            .padding(top = AppTheme.spacing.xsPlus)
                            .size(20.dp)
                            .clip(CircleShape)
                    )
                }
            }

            AppText(
                text = event.competitor2,
                style = AppTheme.typography.body,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(Modifier.height(10.dp))

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(
                        (
                                if (hasExpired) AppTheme.colors.error
                                else AppTheme.colors.countdown
                                ).copy(alpha = 0.12f)
                    )
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppText(
                    text = if (hasExpired) str(R.string.expired)
                    else str(R.string.starts_in, remainingMillis.toCountdownText()),
                    style = AppTheme.typography.caption.copy(
                        color = if (hasExpired) AppTheme.colors.error
                        else AppTheme.colors.countdown
                    )
                )
            }

            Icon(
                imageVector = if (event.isFav) Icons.Default.Star else Icons.Default.StarBorder,
                modifier = Modifier
                    .appClick(bounded = false) {
                        onFavoriteClick(event.id)
                    }
                    .size(24.dp),
                contentDescription = null,
                tint = if (event.isFav) AppTheme.colors.favorite else AppTheme.colors.textPlaceholder
            )
        }
    }
}

private fun Long.toCountdownText(): String {
    var seconds = this / 1000

    val months = seconds / (60 * 60 * 24 * 30)
    seconds %= (60 * 60 * 24 * 30)

    val weeks = seconds / (60 * 60 * 24 * 7)
    seconds %= (60 * 60 * 24 * 7)

    val days = seconds / (60 * 60 * 24)
    seconds %= (60 * 60 * 24)

    val hours = seconds / (60 * 60)
    seconds %= (60 * 60)

    val minutes = seconds / 60
    seconds %= 60

    return buildList {
        if (months > 0) add("${months}mo")
        if (weeks > 0) add("${weeks}w")
        if (days > 0) add("${days}d")
        if (hours > 0) add("${hours}h")
        if (minutes > 0) add("${minutes}m")

        add("${seconds}s")

    }.joinToString(" ")
}

@Preview
@Composable
private fun PreviewItem() {
    AppThemeProvider(AppTheme.Mode.Dark) {
        EventListItem(
            event = EventDomain(
                id = "",
                sportId = "",
                competitor1 = "competitor1 iu h iuahdufh uisdahf oiuhgasdoyuf gy",
                competitor2 = "competitor2",
                time = 10000000L,
                isFav = false
            ),
            sport = "VOLLEYBALL",
            nowMillis = 0L,
        ) {

        }
    }
}