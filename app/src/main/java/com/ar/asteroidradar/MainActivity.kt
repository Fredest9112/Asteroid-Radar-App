package com.ar.asteroidradar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.ar.asteroidradar.navigation.Screen
import com.ar.asteroidradar.navigation.SetupAsteroidRadarNavGraph
import com.ar.asteroidradar.repo.IDataStoreRepo
import com.ar.asteroidradar.ui.theme.AsteroidRadarAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.properties.Delegates

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var keepSplashOpened = true
    @Inject
    lateinit var dataStoreRepo: IDataStoreRepo
    private var onBoardingCompleted by Delegates.notNull<Boolean>()
    private lateinit var startDestination: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            keepSplashOpened
        }

        lifecycleScope.launch {
            onBoardingCompleted = dataStoreRepo.readOnBoardingState().first()
            startDestination = if (onBoardingCompleted) Screen.Home.route else Screen.Welcome.route

            setContent {
                AsteroidRadarAppTheme {
                    Surface {
                        SetupAsteroidRadarNavGraph(
                            navHostController = rememberNavController(),
                            onFinishSplash = {
                                keepSplashOpened = false
                            },
                            startDestination = startDestination
                        )
                    }
                }
            }
        }
    }
}
