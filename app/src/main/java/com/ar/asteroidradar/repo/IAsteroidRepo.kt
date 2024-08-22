package com.ar.asteroidradar.repo

import com.ar.asteroidradar.data.PictureOfDay

interface IAsteroidRepo {
    suspend fun refreshAsteroids()
    suspend fun deleteAsteroids()
    suspend fun refreshPicture(): PictureOfDay
}