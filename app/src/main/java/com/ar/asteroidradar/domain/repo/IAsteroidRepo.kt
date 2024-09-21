package com.ar.asteroidradar.domain.repo

import com.ar.asteroidradar.domain.exceptions.PictureResponse

interface IAsteroidRepo {
    suspend fun refreshAsteroids()
    suspend fun deleteAsteroids()
    suspend fun refreshPicture(): PictureResponse
}