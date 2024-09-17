package com.ar.asteroidradar.domain.exceptions

import com.ar.asteroidradar.data.models.Asteroid
import com.ar.asteroidradar.data.models.PictureOfDayRemote

sealed class NetworkResponse {
    data class AsteroidsSuccess (val message: String, val asteroids: List<Asteroid>): NetworkResponse()
    data class AsteroidsError (val message: String, val asteroids: List<Asteroid> = emptyList()): NetworkResponse()
    data class PictureSuccess (val message: String, val pictureOfDay: PictureOfDayRemote): NetworkResponse()
    data class PictureError (val message: String, val pictureOfDay: PictureOfDayRemote? = null): NetworkResponse()
}