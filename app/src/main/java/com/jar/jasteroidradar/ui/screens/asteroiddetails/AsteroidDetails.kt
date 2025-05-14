package com.jar.jasteroidradar.ui.screens.asteroiddetails

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jar.jasteroidradar.R
import com.jar.jasteroidradar.data.database.AsteroidDB
import com.jar.jasteroidradar.ui.components.asteroiddetails.AsteroidInfoDetail
import com.jar.jasteroidradar.ui.theme.AsteroidRadarAppTheme
import com.jar.jasteroidradar.utils.Constants.ABSOLUTE_MAGNITUDE
import com.jar.jasteroidradar.utils.Constants.ASTEROID_DB_MOCK
import com.jar.jasteroidradar.utils.Constants.CLOSE_APPROACH_DATE
import com.jar.jasteroidradar.utils.Constants.DISTANCE_EARTH
import com.jar.jasteroidradar.utils.Constants.ESTIMATED_DIAMETER
import com.jar.jasteroidradar.utils.Constants.RELATIVE_VELOCITY

@Composable
fun AsteroidDetails(
    asteroidDB: AsteroidDB
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .border(
                    width = 3.dp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    shape = RoundedCornerShape(5),
                )
                .fillMaxWidth()
                .height(220.dp)
                .clip(RoundedCornerShape(10.dp))
        ) {
            Image(
                painter = if (asteroidDB.isPotentiallyHazardous) {
                    painterResource(id = R.drawable.asteroid_hazardous)
                } else {
                    painterResource(id = R.drawable.asteroid_safe)

                },
                contentDescription = "Hazardous asteroid",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.padding(all = 5.dp))
        AsteroidInfoDetail(
            titleText = CLOSE_APPROACH_DATE,
            infoText = asteroidDB.closeApproachDate,
            hasHelpIcon = false
        )
        AsteroidInfoDetail(
            titleText = ABSOLUTE_MAGNITUDE,
            infoText = asteroidDB.absoluteMagnitude.toString(),
        )
        AsteroidInfoDetail(
            titleText = ESTIMATED_DIAMETER,
            infoText = asteroidDB.estimatedDiameter.toString(),
            hasHelpIcon = false
        )
        AsteroidInfoDetail(
            titleText = RELATIVE_VELOCITY,
            infoText = asteroidDB.relativeVelocity.toString(),
            hasHelpIcon = false
        )
        AsteroidInfoDetail(
            titleText = DISTANCE_EARTH,
            infoText = asteroidDB.distanceFromEarth.toString(),
            hasHelpIcon = false
        )
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
            AsteroidDetails(
                asteroidDB = ASTEROID_DB_MOCK
            )
        }
    }
}