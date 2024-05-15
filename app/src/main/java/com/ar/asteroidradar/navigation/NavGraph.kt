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
import com.ar.asteroidradar.ui.screens.HomeScreen
import com.ar.asteroidradar.ui.screens.welcome.WelcomeScreen
import com.ar.asteroidradar.ui.screens.welcome.WelcomeViewModel
import com.ar.asteroidradar.utils.Constants.ASTEROID_ID_KEY

@Composable
fun SetupAsteroidRadarNavGraph(
    navHostController: NavHostController,
    onFinishSplash: () -> Unit
) {
    val welcomeViewModel: WelcomeViewModel = hiltViewModel()
    val onBoardingCompleted by welcomeViewModel.onBoardingCompleted.collectAsState()

    LaunchedEffect(key1 = Unit) {
        onFinishSplash()
    }

    NavHost(
        navController = navHostController,
        startDestination = if (onBoardingCompleted) Screen.Home.route else Screen.Welcome.route
    ) {
        composable(route = Screen.Welcome.route) {
            if (onBoardingCompleted) {
                navHostController.popBackStack()
                navHostController.navigate(Screen.Home.route)
            } else {
                WelcomeScreen(
                    navHostController = navHostController,
                    welcomeViewModel = welcomeViewModel
                )
            }
        }
        composable(route = Screen.Home.route) {
            HomeScreen()
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