package com.ar.asteroidradar.ui.navigation

import android.net.Uri
import com.ar.asteroidradar.utils.Constants.ASTEROID_DETAILED_IMAGE_KEY
import com.ar.asteroidradar.utils.Constants.ASTEROID_DETAILED_KEY

sealed class Screen(val route: String = ""){
    object Splash: Screen(route = "splash_screen")
    object Welcome: Screen(route = "welcome_screen")
    object Home: Screen(route = "home_screen")
    object AsteroidDetailImage: Screen(route = "$ASTEROID_DETAILED_IMAGE_KEY/{url}/{explanation}") {
        fun asteroidDetailImage(url: String, explanation: String): String {
            return "$ASTEROID_DETAILED_IMAGE_KEY/${Uri.encode(url)}/${Uri.encode(explanation)}"
        }
    }
    object AsteroidDetails: Screen(route = "$ASTEROID_DETAILED_KEY/{asteroidId}") {
        fun asteroidId(asteroidId: Int): String {
            return "$ASTEROID_DETAILED_KEY/$asteroidId"
        }
    }
}