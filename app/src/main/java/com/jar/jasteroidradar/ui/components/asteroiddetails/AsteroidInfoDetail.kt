package com.jar.jasteroidradar.ui.components.asteroiddetails

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.jar.jasteroidradar.R
import com.jar.jasteroidradar.data.database.AsteroidDB
import com.jar.jasteroidradar.ui.theme.AsteroidRadarAppTheme

@Composable
fun AsteroidInfoDetail(
    titleText: String,
    infoText: String,
    asteroidDB: AsteroidDB? = null,
    hasHelpIcon: Boolean = true,
    onAsteroidClicked: (AsteroidDB) -> Unit,
) {
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
            .clickable {
                asteroidDB?.let { onAsteroidClicked(it) }
            }
    ) {
        Column (
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(all = 5.dp)
        ) {
            Text(
                text = titleText,
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
            Text(
                text = infoText,
                fontSize = MaterialTheme.typography.labelSmall.fontSize
            )
        }
        when {
            hasHelpIcon -> {
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_help_circle
                    ),
                    contentDescription = "help",
                    modifier = Modifier
                        .padding(all = 5.dp)
                )
            }
            !hasHelpIcon -> {
                when(asteroidDB?.isPotentiallyHazardous) {
                    true -> {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.bad_feeling_asteroid
                            ),
                            contentDescription = "bad feeling about asteroid",
                            modifier = Modifier
                                .padding(all = 5.dp),
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                    false -> {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.good_feeling_asteroid
                            ),
                            contentDescription = "good feeling about asteroid",
                            modifier = Modifier
                                .padding(all = 5.dp)
                        )
                    }
                    else -> {}
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
fun HomeScreenPreview(){
    AsteroidRadarAppTheme {
        Surface {
            AsteroidInfoDetail(
                titleText = "Close date approach",
                infoText = "DD-MM-YY",
                onAsteroidClicked = {  }
            )
        }
    }
}