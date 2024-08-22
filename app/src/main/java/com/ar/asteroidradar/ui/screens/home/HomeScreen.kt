package com.ar.asteroidradar.ui.screens.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ar.asteroidradar.data.Constants.PICTURE_OF_DAY_MOCK
import com.ar.asteroidradar.data.PictureOfDay
import com.ar.asteroidradar.ui.components.home.AsteroidDailyImage
import com.ar.asteroidradar.ui.theme.AsteroidRadarAppTheme

@Composable
fun HomeScreen(pictureOfDay: PictureOfDay) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Asteroid Radar",
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
        )
        AsteroidDailyImage(pictureOfDay = pictureOfDay)
    }
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
fun HomeScreenPreview(){
    AsteroidRadarAppTheme {
        Surface {
            HomeScreen(
                pictureOfDay = PICTURE_OF_DAY_MOCK
            )
        }
    }
}