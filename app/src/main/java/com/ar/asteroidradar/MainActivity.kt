package com.ar.asteroidradar

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ar.asteroidradar.ui.theme.AsteroidRadarAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AsteroidRadarAppTheme {
                Surface {
                    AsteroidRadarApp()
                }
            }
        }
    }
}

@Preview (
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview (
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Composable
fun AsteroidRadarAppPreview(){
    AsteroidRadarAppTheme {
        Surface {
            AsteroidRadarApp()
        }
    }
}