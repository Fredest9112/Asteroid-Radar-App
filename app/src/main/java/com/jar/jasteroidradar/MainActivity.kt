package com.jar.jasteroidradar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.jar.jasteroidradar.ui.navigation.SetupAsteroidRadarNavGraph
import com.jar.jasteroidradar.ui.theme.AsteroidRadarAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var keepSplashOpened = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            keepSplashOpened
        }

        setContent {
            AsteroidRadarAppTheme {
                Surface {
                    SetupAsteroidRadarNavGraph(
                        navHostController = rememberNavController(),
                        onFinishSplash = {
                            keepSplashOpened = false
                        }
                    )
                }
            }
        }
    }
}
