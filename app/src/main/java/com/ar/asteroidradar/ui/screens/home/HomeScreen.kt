package com.ar.asteroidradar.ui.screens.home

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ar.asteroidradar.data.database.AsteroidDB
import com.ar.asteroidradar.domain.entities.PictureOfDay
import com.ar.asteroidradar.domain.entities.asDomainEntity
import com.ar.asteroidradar.domain.states.AsteroidDataState
import com.ar.asteroidradar.domain.states.AsteroidTimeState
import com.ar.asteroidradar.domain.states.PictureState
import com.ar.asteroidradar.ui.components.home.AsteroidDailyImage
import com.ar.asteroidradar.ui.components.home.AsteroidHolder
import com.ar.asteroidradar.ui.theme.AsteroidRadarAppTheme
import com.ar.asteroidradar.utils.Constants.ASTEROIDS_MOCK
import com.ar.asteroidradar.utils.Constants.DATE_OPTIONS
import com.ar.asteroidradar.utils.Constants.PICTURE_OF_DAY_MOCK

@Composable
fun HomeScreen(
    pictureOfDay: PictureOfDay,
    pictureState: PictureState,
    shouldShowHomeError: Pair<Boolean, String>,
    asteroids: List<AsteroidDB>,
    asteroidDataState: AsteroidDataState,
    selectedOption: AsteroidTimeState,
    onOptionSelected: (String) -> Unit
) {
    if (shouldShowHomeError.first) {
        Toast.makeText(LocalContext.current, shouldShowHomeError.second, Toast.LENGTH_SHORT).show()
    }
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
        AsteroidDailyImage(
            pictureOfDay = pictureOfDay,
            pictureState = pictureState
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            DATE_OPTIONS.forEach { timeOption ->
                Column (
                    modifier = Modifier
                        .height(100.dp)
                        .selectable(
                            onClick = { onOptionSelected(timeOption) },
                            selected = (timeOption == selectedOption.dateState),
                            role = Role.RadioButton
                        )
                        .padding(all = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center

                ) {
                    RadioButton(
                        selected = (timeOption == selectedOption.dateState),
                        onClick = null
                    )
                    Text(
                        text = timeOption,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
        LazyColumn {
            items(
                items = asteroids,
                key = { it.id }
            ) {
                AsteroidHolder(
                    asteroid = it,
                    asteroidDataState = asteroidDataState
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
                onOptionSelected = {  }
            )
        }
    }
}