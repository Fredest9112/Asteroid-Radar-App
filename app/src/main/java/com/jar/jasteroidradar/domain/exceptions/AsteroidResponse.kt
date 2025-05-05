package com.jar.jasteroidradar.domain.exceptions

import com.jar.jasteroidradar.data.database.AsteroidDB

sealed class AsteroidResponse {
    data class AsteroidsSuccess (val asteroids: List<AsteroidDB>): AsteroidResponse()
    data class AsteroidsError (val exception: Throwable): AsteroidResponse()
}