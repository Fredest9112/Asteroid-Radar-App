package com.jar.jasteroidradar.ui.components.home

import android.content.res.Configuration
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jar.jasteroidradar.data.database.AsteroidDB
import com.jar.jasteroidradar.domain.states.AsteroidDataState
import com.jar.jasteroidradar.ui.components.asteroiddetails.AsteroidInfoDetail
import com.jar.jasteroidradar.ui.theme.AsteroidRadarAppTheme
import com.jar.jasteroidradar.utils.Constants.ASTEROIDS_DB_MOCK

@Composable
fun AsteroidsHolder(
    asteroids: List<AsteroidDB>,
    asteroidDataState: AsteroidDataState
) {
    LazyColumn {
        items(
            items = asteroids,
            key = { it.id }
        ) { asteroid ->
            when (asteroidDataState) {
                AsteroidDataState.LOADING -> {
                    LoadingIndicator()
                }
                else -> {
                    AsteroidInfoDetail(
                        titleText = asteroid.codename,
                        infoText = asteroid.closeApproachDate,
                        hasHelpIcon = false,
                        asteroidDB = asteroid
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
fun AsteroidsHolderPreview() {
    AsteroidRadarAppTheme {
        Surface {
            AsteroidsHolder(
                asteroids = ASTEROIDS_DB_MOCK,
                asteroidDataState = AsteroidDataState.COMPLETED
            )
        }
    }
}