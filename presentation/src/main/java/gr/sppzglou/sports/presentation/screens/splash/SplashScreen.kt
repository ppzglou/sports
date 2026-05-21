package gr.sppzglou.sports.presentation.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import gr.sppzglou.sports.presentation.R


@Composable
fun SplashScreen() {
    Box(Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.mipmap.logo),
            "logo",
            Modifier
                .fillMaxWidth(0.6f)
                .align(Alignment.Center),
        )
    }
}