package com.ar.asteroidradar.ui.components.home

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ar.asteroidradar.R
import com.ar.asteroidradar.data.database.AsteroidDB
import com.ar.asteroidradar.domain.states.AsteroidDataState
import com.ar.asteroidradar.ui.theme.AsteroidRadarAppTheme
import com.ar.asteroidradar.utils.Constants.ASTEROID_MOCK

@Composable
fun AsteroidHolder(
    asteroid: AsteroidDB,
    asteroidDataState: AsteroidDataState) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(all = 10.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer
            )
    ) {
        when (asteroidDataState) {
            AsteroidDataState.LOADING -> {
                Column(
                    modifier = Modifier
                        .padding(all = 10.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp)
                    )
                }
            }
            else -> {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .padding(all = 5.dp)
                ) {
                    Text(
                        text = asteroid.codename,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    )
                    Text(
                        text = asteroid.closeApproachDate,
                        fontSize = MaterialTheme.typography.labelSmall.fontSize
                    )
                }
                if(asteroid.isPotentiallyHazardous){
                    Icon(
                        painter = painterResource(
                            id = R.drawable.bad_feeling_asteroid
                        ),
                        contentDescription = "bad feeling about asteroid",
                        modifier = Modifier
                            .padding(all = 5.dp),
                        tint = MaterialTheme.colorScheme.error
                    )
                } else {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.good_feeling_asteroid
                        ),
                        contentDescription = "good feeling about asteroid",
                        modifier = Modifier
                            .padding(all = 5.dp)
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
fun AsteroidHolderPreview() {
    AsteroidRadarAppTheme {
        Surface {
            AsteroidHolder(
                asteroid = ASTEROID_MOCK,
                asteroidDataState = AsteroidDataState.COMPLETED
            )
        }
    }
}