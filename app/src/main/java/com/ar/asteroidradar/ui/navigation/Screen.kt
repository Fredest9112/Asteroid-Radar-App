package com.ar.asteroidradar.ui.navigation

sealed class Screen(val route: String = ""){
    object Splash: Screen(route = "splash_screen")
    object Welcome: Screen(route = "welcome_screen")
    object Home: Screen(route = "home_screen")
    object AsteroidDetails: Screen(route = "asteroiddetails_screen/{asteroidId}"){
        fun asteroidId(asteroidId: Int): String {
            return "asteroiddetails_screen/$asteroidId"
        }
    }
}