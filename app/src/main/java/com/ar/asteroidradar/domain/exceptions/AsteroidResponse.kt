package com.ar.asteroidradar.domain.exceptions

import com.ar.asteroidradar.data.models.AsteroidRemote

sealed class AsteroidResponse {
    data class AsteroidsSuccess (val message: String, val asteroids: List<AsteroidRemote>): AsteroidResponse()
    data class AsteroidsError (val exception: Throwable, val asteroids: List<AsteroidRemote> = emptyList()): AsteroidResponse()
}