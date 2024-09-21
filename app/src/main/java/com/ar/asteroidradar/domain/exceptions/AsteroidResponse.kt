package com.ar.asteroidradar.domain.exceptions

import com.ar.asteroidradar.data.models.Asteroid

sealed class AsteroidResponse {
    data class AsteroidsSuccess (val message: String, val asteroids: List<Asteroid>): AsteroidResponse()
    data class AsteroidsError (val exception: Throwable, val asteroids: List<Asteroid> = emptyList()): AsteroidResponse()
}