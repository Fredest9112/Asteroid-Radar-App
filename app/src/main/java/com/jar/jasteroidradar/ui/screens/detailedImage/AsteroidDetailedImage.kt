package com.jar.jasteroidradar.ui.screens.detailedImage

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jar.jasteroidradar.ui.theme.AsteroidRadarAppTheme
import com.jar.jasteroidradar.utils.Constants.PICTURE_OF_DAY_MOCK

@Composable
fun AsteroidDetailedImage(
    urlPicture: String,
    explanationPicture: String
) {
    var imageClicked by remember { mutableStateOf(value = false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = explanationPicture,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(top = 20.dp)
        )
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(data = urlPicture)
                .build(),
            contentDescription = "Picture of the day",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(all = 8.dp)
                .height(220.dp)
                .fillMaxWidth()
                .clickable { imageClicked = true }
        )
        if (imageClicked) {
            AnimatedVisibility(visible = urlPicture.isNotEmpty()) {
                AsteroidZoomableImage(
                    selectedImage = urlPicture,
                    onCloseClicked = {
                        imageClicked = false
                    }
                )
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
            AsteroidDetailedImage(
                urlPicture = PICTURE_OF_DAY_MOCK.url,
                explanationPicture = PICTURE_OF_DAY_MOCK.explanation
            )
        }
    }
}