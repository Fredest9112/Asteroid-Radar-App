package com.jar.jasteroidradar.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jar.jasteroidradar.domain.states.OnBoardingState
import com.jar.jasteroidradar.ui.components.error.ToastError
import com.jar.jasteroidradar.ui.screens.asteroiddetails.AsteroidDetails
import com.jar.jasteroidradar.ui.screens.asteroiddetails.AsteroidDetailsViewModel
import com.jar.jasteroidradar.ui.screens.detailedImage.AsteroidDetailedImage
import com.jar.jasteroidradar.ui.screens.home.HomeScreen
import com.jar.jasteroidradar.ui.screens.home.HomeScreenViewModel
import com.jar.jasteroidradar.ui.screens.splash.SplashScreen
import com.jar.jasteroidradar.ui.screens.welcome.WelcomeScreen
import com.jar.jasteroidradar.ui.screens.welcome.WelcomeViewModel
import com.jar.jasteroidradar.utils.Constants.PICTURE_OF_DAY_MOCK

@Composable
fun SetupAsteroidRadarNavGraph(
    navHostController: NavHostController,
    onFinishSplash: () -> Unit,
    innerPaddingValues: PaddingValues
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
        startDestination = startDestination,
        modifier = Modifier
            .padding(innerPaddingValues)
    ) {
        composable<Screen.Splash> {
            SplashScreen()
        }
        composable<Screen.Welcome> {
            val welcomeViewModel: WelcomeViewModel = hiltViewModel()
            WelcomeScreen(
                onNavigateToHomeScreen = {
                    navHostController.popBackStack()
                    welcomeViewModel.saveOnBoardingState(complete = true)
                    navHostController.navigate(Screen.Home)
                }
            )
        }
        composable<Screen.Home> {
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
                        Screen.AsteroidDetailImage(
                            url = chosenPictureOfDay.url,
                            explanation = chosenPictureOfDay.explanation
                        )
                    )
                },
                onAsteroidClicked = { chosenAsteroid ->
                    navHostController.navigate(
                        Screen.AsteroidDetails(
                            asteroidId = chosenAsteroid.id
                        )
                    )
                }
            )
        }
        composable<Screen.AsteroidDetails> { backStackEntry ->
            val args = backStackEntry.toRoute<Screen.AsteroidDetails>()
            val asteroidId = args.asteroidId
            val asteroidDetailsViewModel: AsteroidDetailsViewModel = hiltViewModel()
            asteroidId?.let { asteroidDetailsViewModel.getAsteroidById(it) }
            val asteroidDataState by asteroidDetailsViewModel.asteroidDataState.collectAsState()
            val asteroid by asteroidDetailsViewModel.asteroid.collectAsState()
            AsteroidDetails(
                asteroidDB = asteroid,
                asteroidDataState = asteroidDataState
            )
        }
        composable<Screen.AsteroidDetailImage> { backStackEntry ->
            val args = backStackEntry.toRoute<Screen.AsteroidDetailImage>()
            AsteroidDetailedImage(
                urlPicture = args.url ?: PICTURE_OF_DAY_MOCK.url,
                explanationPicture = args.explanation ?: PICTURE_OF_DAY_MOCK.explanation
            )
        }
    }

    ToastError(
        isThereAnError = shouldShowNavError.first,
        message = shouldShowNavError.second,
        onErrorMessageShown = { navigationViewModel.errorShown() }
    )
}