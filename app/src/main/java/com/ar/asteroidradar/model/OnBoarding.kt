package com.ar.asteroidradar.model

import com.ar.asteroidradar.R

sealed class OnBoarding(
    val image: Int,
    val title: Int,
    val description: Int
) {
    object FirstScreen: OnBoarding(
        image = R.drawable.asteroids_welcome,
        title = R.string.onboarding1_title,
        description = R.string.onboarding1_message
    )

    object SecondScreen: OnBoarding(
        image = R.drawable.asteroid_safe,
        title = R.string.onboarding2_title,
        description = R.string.onboarding2_message
    )

    object ThirdScreen: OnBoarding(
        image = R.drawable.asteroid_hazardous,
        title = R.string.onboarding3_title,
        description = R.string.onboarding3_message
    )
}