package com.jar.jasteroidradar.ui.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    object Splash : Screen()

    @Serializable
    object Welcome : Screen()

    @Serializable
    object Home : Screen()

    @Serializable
    data class AsteroidDetailImage(
        val url: String?,
        val explanation: String?
    ) : Screen()

    @Serializable
    data class AsteroidDetails(
        val asteroidId: Long?
    ) : Screen()
}
