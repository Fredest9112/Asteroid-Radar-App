package com.ar.asteroidradar.domain.exceptions

import com.ar.asteroidradar.data.database.AsteroidDB

sealed class AsteroidResponse {
    data class AsteroidsSuccess (val asteroids: List<AsteroidDB>): AsteroidResponse()
    data class AsteroidsError (val exception: Throwable): AsteroidResponse()
}