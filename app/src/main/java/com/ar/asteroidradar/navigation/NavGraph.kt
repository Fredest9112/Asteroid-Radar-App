package com.ar.asteroidradar.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.ar.asteroidradar.utils.OnBoardingState

@Composable
fun SetupAsteroidRadarNavGraph(
    navHostController: NavHostController,
    onFinishSplash: () -> Unit
) {
    val navigationViewModel: NavigationViewModel = hiltViewModel()
    val onBoardingState by navigationViewModel.onBoardingState.collectAsState()
    val startDestination = when (onBoardingState) {
        OnBoardingState.LOADING -> {
            Screen.Splash.route
        }
        OnBoardingState.COMPLETED -> {
            onFinishSplash()
            Screen.Home.route
        }
        OnBoardingState.NOT_COMPLETED -> {
            onFinishSplash()
            Screen.Welcome.route
        }
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
            val pictureOfDay by homeScreenViewModel.pictureOfDay.collectAsState()
            HomeScreen(pictureOfDay = pictureOfDay)
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