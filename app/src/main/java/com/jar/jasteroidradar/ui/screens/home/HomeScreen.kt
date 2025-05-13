package com.jar.jasteroidradar.ui.screens.home

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
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
import com.jar.jasteroidradar.data.database.AsteroidDB
import com.jar.jasteroidradar.domain.entities.PictureOfDay
import com.jar.jasteroidradar.domain.entities.asDomainEntity
import com.jar.jasteroidradar.domain.states.AsteroidDataState
import com.jar.jasteroidradar.domain.states.AsteroidTimeState
import com.jar.jasteroidradar.domain.states.PictureState
import com.jar.jasteroidradar.ui.components.error.ToastError
import com.jar.jasteroidradar.ui.components.home.AsteroidDailyImage
import com.jar.jasteroidradar.ui.components.home.AsteroidsHolder
import com.jar.jasteroidradar.ui.components.home.DateChooser
import com.jar.jasteroidradar.ui.theme.AsteroidRadarAppTheme
import com.jar.jasteroidradar.utils.Constants.ASTEROIDS_MOCK
import com.jar.jasteroidradar.utils.Constants.PICTURE_OF_DAY_MOCK

@Composable
fun HomeScreen(
    pictureOfDay: PictureOfDay,
    pictureState: PictureState,
    shouldShowHomeError: Pair<Boolean, String>,
    asteroids: List<AsteroidDB>,
    asteroidDataState: AsteroidDataState,
    selectedOption: AsteroidTimeState,
    onOptionSelected: (String) -> Unit,
    onErrorMessageShown: () -> Unit,
    onImageClicked: (PictureOfDay) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "JAsteroid Radar",
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
        )
        AsteroidDailyImage(
            pictureOfDay = pictureOfDay,
            pictureState = pictureState,
            onImageClicked = onImageClicked
        )
        AnimatedVisibility(visible = asteroidDataState == AsteroidDataState.COMPLETED) {
            DateChooser(
                onOptionSelected = onOptionSelected,
                selectedOption = selectedOption
            )
        }
        AsteroidsHolder(
            asteroids = asteroids,
            asteroidDataState = asteroidDataState
        )
    }
    ToastError(
        isThereAnError = shouldShowHomeError.first,
        message = shouldShowHomeError.second,
        onErrorMessageShown = onErrorMessageShown
    )
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
                pictureOfDay = PICTURE_OF_DAY_MOCK,
                pictureState = PictureState.COMPLETED,
                shouldShowHomeError = Pair(false,""),
                asteroids = ASTEROIDS_MOCK.asDomainEntity(),
                asteroidDataState = AsteroidDataState.COMPLETED,
                selectedOption = AsteroidTimeState.TODAY,
                onOptionSelected = { },
                onErrorMessageShown = { },
                onImageClicked = { }
            )
        }
    }
}