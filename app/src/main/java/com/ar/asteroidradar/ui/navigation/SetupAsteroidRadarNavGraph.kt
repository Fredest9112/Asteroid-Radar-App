package com.ar.asteroidradar.ui.navigation

import android.net.Uri
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
import com.ar.asteroidradar.domain.states.OnBoardingState
import com.ar.asteroidradar.ui.components.error.ToastError
import com.ar.asteroidradar.ui.screens.detailedImage.AsteroidDetailedImage
import com.ar.asteroidradar.ui.screens.home.HomeScreen
import com.ar.asteroidradar.ui.screens.home.HomeScreenViewModel
import com.ar.asteroidradar.ui.screens.splash.SplashScreen
import com.ar.asteroidradar.ui.screens.welcome.WelcomeScreen
import com.ar.asteroidradar.ui.screens.welcome.WelcomeViewModel
import com.ar.asteroidradar.utils.Constants.ASTEROID_ID_KEY
import com.ar.asteroidradar.utils.Constants.PICTURE_OF_DAY_MOCK

@Composable
fun SetupAsteroidRadarNavGraph(
    navHostController: NavHostController,
    onFinishSplash: () -> Unit
) {
    val navigationViewModel: NavigationViewModel = hiltViewModel()
    val onBoardingState by navigationViewModel.onBoardingState.collectAsState()
    val startDestination by navigationViewModel.startDestination.collectAsState()
    val shouldShowNavError by navigationViewModel.shouldShowError.collectAsState()

    LaunchedEffect(key1 = onBoardingState) {
        if (onBoardingState != OnBoardingState.LOADING) {
            onFinishSplash()
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
            val asteroids by homeScreenViewModel.asteroids.collectAsState()
            val asteroidDataState by homeScreenViewModel.asteroidDataState.collectAsState()
            val pictureOfDay by homeScreenViewModel.pictureOfDay.collectAsState()
            val pictureState by homeScreenViewModel.pictureState.collectAsState()
            val selectedOption by homeScreenViewModel.selectedOption.collectAsState()
            val shouldShowHomeError by homeScreenViewModel.shouldShowHomeError.collectAsState()
            HomeScreen(
                asteroids = asteroids,
                asteroidDataState = asteroidDataState,
                pictureOfDay = pictureOfDay,
                pictureState = pictureState,
                shouldShowHomeError = shouldShowHomeError,
                selectedOption = selectedOption,
                onOptionSelected = { homeScreenViewModel.onOptionSelected(it) },
                onErrorMessageShown = { homeScreenViewModel.errorShown() },
                onImageClicked = { chosenPictureOfDay ->
                    navHostController.navigate(
                        Screen.AsteroidDetailImage.asteroidDetailImage(
                            url = chosenPictureOfDay.url,
                            explanation = chosenPictureOfDay.explanation
                        )
                    )
                }
            )
        }
        composable(
            route = Screen.AsteroidDetails.route,
            arguments = listOf(navArgument(name = ASTEROID_ID_KEY) {
                type = NavType.StringType
            })
        ) {

        }
        composable(
            route = Screen.AsteroidDetailImage.route,
            arguments = listOf(
                navArgument(name = "url") { type = NavType.StringType },
                navArgument(name = "explanation") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val urlPicture = backStackEntry.arguments?.getString("url").let { Uri.decode(it) }
                ?: PICTURE_OF_DAY_MOCK.url
            val explanationPicture =
                backStackEntry.arguments?.getString("explanation").let { Uri.decode(it) }
                    ?: PICTURE_OF_DAY_MOCK.explanation
            AsteroidDetailedImage(
                urlPicture = urlPicture,
                explanationPicture = explanationPicture
            )
        }
    }

    ToastError(
        isThereAnError = shouldShowNavError.first,
        message = shouldShowNavError.second,
        onErrorMessageShown = { navigationViewModel.errorShown() }
    )
}