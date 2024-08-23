package com.ar.asteroidradar.ui.screens.splash

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ar.asteroidradar.ui.theme.AsteroidRadarAppTheme

@Composable
fun SplashScreen() {
    Box(modifier = Modifier.fillMaxSize())
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Composable
fun SplashScreenPreview() {
    AsteroidRadarAppTheme {
        Surface {
            SplashScreen()
        }
    }
}