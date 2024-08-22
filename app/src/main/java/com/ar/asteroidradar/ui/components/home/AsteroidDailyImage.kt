package com.ar.asteroidradar.ui.components.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ar.asteroidradar.data.Constants.PICTURE_OF_DAY_MOCK
import com.ar.asteroidradar.data.PictureOfDay
import com.ar.asteroidradar.ui.theme.AsteroidRadarAppTheme

@Composable
fun AsteroidDailyImage(
    pictureOfDay: PictureOfDay
){
    Column(
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Image of the day",
            modifier = Modifier.padding(all = 8.dp)
        )
        Box {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data = pictureOfDay.url)
                    .build(),
                contentDescription = "Picture of the day",
                contentScale = ContentScale.Crop
            )
            Text(
                text = pictureOfDay.copyright,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                modifier = Modifier
                    .padding(all = 8.dp)
                    .align(Alignment.BottomStart)
            )
            Text(
                text = pictureOfDay.date,
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                modifier = Modifier
                    .padding(all = 8.dp)
                    .align(Alignment.TopEnd)
            )
        }
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
            AsteroidDailyImage(
                pictureOfDay = PICTURE_OF_DAY_MOCK
            )
        }
    }
}