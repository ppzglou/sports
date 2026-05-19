package gr.sppzglou.sports.presentation.screens.dash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import gr.sppzglou.sports.presentation.R
import gr.sppzglou.sports.presentation.components.AppText
import gr.sppzglou.sports.presentation.theme.AppTheme
import gr.sppzglou.sports.presentation.utils.LettersFormat
import gr.sppzglou.sports.presentation.utils.str


@Composable
fun DashboardScreen() {
    Box(Modifier.fillMaxSize()) {
        AppText(
            str(R.string.dashboard, LettersFormat.Uppercase),
            Modifier.align(Alignment.Center),
            style = AppTheme.typography.h1,
        )
    }
}