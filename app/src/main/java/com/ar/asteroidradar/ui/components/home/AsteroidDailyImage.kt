package com.ar.asteroidradar.ui.components.home

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ar.asteroidradar.R
import com.ar.asteroidradar.domain.entities.PictureOfDay
import com.ar.asteroidradar.domain.states.PictureState
import com.ar.asteroidradar.ui.theme.AsteroidRadarAppTheme
import com.ar.asteroidradar.utils.Constants.PICTURE_OF_DAY_MOCK

@Composable
fun AsteroidDailyImage(
    pictureOfDay: PictureOfDay,
    pictureState: PictureState
) {
    Box(
        modifier = Modifier
            .padding(all = 10.dp)
            .border(
                width = 3.dp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                shape = RoundedCornerShape(5),
            )
            .fillMaxWidth()
            .height(220.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        when (pictureState) {
            PictureState.COMPLETED -> {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(data = pictureOfDay.url)
                        .build(),
                    contentDescription = "Picture of the day",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Column(
                    modifier = Modifier.padding(all = 8.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Image of the day:",
                            fontSize = MaterialTheme.typography.titleLarge.fontSize
                        )
                        Text(
                            text = pictureOfDay.date,
                            fontSize = MaterialTheme.typography.titleSmall.fontSize
                        )
                    }
                    Text(
                        text = pictureOfDay.title,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = pictureOfDay.copyright,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    )
                }
            }

            PictureState.LOADING -> {
                LoadingIndicator()
            }

            PictureState.ERROR -> {
                Image(
                    painter = painterResource(id = R.drawable.picture_error),
                    contentDescription = "Picture of the day",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Column(
                    modifier = Modifier.padding(all = 8.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Image of the day:",
                            fontSize = MaterialTheme.typography.titleLarge.fontSize
                        )
                        Text(
                            text = pictureOfDay.date,
                            fontSize = MaterialTheme.typography.titleSmall.fontSize
                        )
                    }
                    Text(
                        text = pictureOfDay.title,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = pictureOfDay.copyright,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    )
                }
            }
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
fun HomeScreenPreview() {
    AsteroidRadarAppTheme {
        Surface {
            AsteroidDailyImage(
                pictureOfDay = PICTURE_OF_DAY_MOCK,
                pictureState = PictureState.ERROR
            )
        }
    }
}