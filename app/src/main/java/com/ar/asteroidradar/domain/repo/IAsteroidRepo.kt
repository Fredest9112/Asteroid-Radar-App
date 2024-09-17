package com.ar.asteroidradar.domain.repo

import com.ar.asteroidradar.data.models.PictureOfDayRemote

interface IAsteroidRepo {
    suspend fun refreshAsteroids()
    suspend fun deleteAsteroids()
    suspend fun refreshPicture(): PictureOfDayRemote
}