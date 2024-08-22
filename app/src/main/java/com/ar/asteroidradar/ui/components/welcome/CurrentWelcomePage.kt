package com.ar.asteroidradar.ui.components.welcome

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ar.asteroidradar.ui.screens.welcome.OnBoarding
import com.ar.asteroidradar.ui.theme.AsteroidRadarAppTheme

@Composable
fun CurrentWelcomePage(
    onBoarding: OnBoarding
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularImage(
            onBoarding = onBoarding,
            size = 250
        )
        Text(
            modifier = Modifier.padding(all = 20.dp),
            text = stringResource(id = onBoarding.title),
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.padding(all = 20.dp),
            text = stringResource(id = onBoarding.description),
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            textAlign = TextAlign.Center
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
fun CurrentPage1(){
    AsteroidRadarAppTheme {
        Surface {
            CurrentWelcomePage(onBoarding = OnBoarding.FirstScreen)
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
fun CurrentPage2(){
    AsteroidRadarAppTheme {
        Surface {
            CurrentWelcomePage(onBoarding = OnBoarding.SecondScreen)
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
fun CurrentPage3(){
    AsteroidRadarAppTheme {
        Surface {
            CurrentWelcomePage(onBoarding = OnBoarding.ThirdScreen)
        }
    }
}