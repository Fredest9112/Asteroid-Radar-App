package com.ar.asteroidradar.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.ar.asteroidradar.ui.screens.welcome.WelcomeScreen
import com.ar.asteroidradar.ui.screens.welcome.WelcomeViewModel
import com.ar.asteroidradar.utils.Constants.ASTEROID_ID_KEY

@Composable
fun SetupAsteroidRadarNavGraph(
    navHostController: NavHostController,
    onFinishSplash: () -> Unit,
    startDestination: String
) {

    LaunchedEffect(key1 = Unit) {
        onFinishSplash()
    }

    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ) {
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