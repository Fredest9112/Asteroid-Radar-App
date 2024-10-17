package com.ar.asteroidradar.ui.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ar.asteroidradar.ui.screens.home.HomeScreen
import com.ar.asteroidradar.ui.screens.home.HomeScreenViewModel
import com.ar.asteroidradar.ui.screens.splash.SplashScreen
import com.ar.asteroidradar.ui.screens.welcome.WelcomeScreen
import com.ar.asteroidradar.ui.screens.welcome.WelcomeViewModel
import com.ar.asteroidradar.utils.Constants.ASTEROID_ID_KEY
import com.ar.asteroidradar.domain.states.OnBoardingState

@Composable
fun SetupAsteroidRadarNavGraph(
    navHostController: NavHostController,
    onFinishSplash: () -> Unit
) {
    val navigationViewModel: NavigationViewModel = hiltViewModel()
    val onBoardingState by navigationViewModel.onBoardingState.collectAsState()
    val startDestination by navigationViewModel.startDestination.collectAsState()
    val shouldShowNavError by navigationViewModel.shouldShowError.collectAsState()
    if (onBoardingState != OnBoardingState.LOADING) {
        onFinishSplash()
    }
    if (shouldShowNavError.first) {
        Toast.makeText(LocalContext.current, shouldShowNavError.second, Toast.LENGTH_SHORT).show()
    }

    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen()
        }
        composable(route = Screen.Welcome.route) {
            val welcomeViewModel: WelcomeViewModel = hiltViewModel()
            WelcomeScreen(
                onNavigateToHomeScreen = {
                    navHostController.popBackStack()
                    welcomeViewModel.saveOnBoardingState(complete = true)
                    navHostController.navigate(Screen.Home.route)
                }
            )
        }
        composable(route = Screen.Home.route) {
            val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
            val asteroids by homeScreenViewModel.asteroids.collectAsState()
            val asteroidDataState by homeScreenViewModel.asteroidDataState.collectAsState()
            val pictureOfDay by homeScreenViewModel.pictureOfDay.collectAsState()
            val pictureState by homeScreenViewModel.pictureState.collectAsState()
            val shouldShowHomeError by homeScreenViewModel.shouldShowHomeError.collectAsState()
            HomeScreen(
                asteroids = asteroids,
                asteroidDataState = asteroidDataState,
                pictureOfDay = pictureOfDay,
                pictureState = pictureState,
                shouldShowHomeError = shouldShowHomeError
            )
        }
        composable(
            route = Screen.AsteroidDetails.route,
            arguments = listOf(navArgument(name = ASTEROID_ID_KEY) {
                type = NavType.StringType
            })
        ) {

        }
    }
}