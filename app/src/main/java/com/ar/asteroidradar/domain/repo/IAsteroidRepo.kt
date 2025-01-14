package com.ar.asteroidradar.domain.repo

import com.ar.asteroidradar.domain.exceptions.AsteroidResponse
import com.ar.asteroidradar.domain.exceptions.PictureResponse
import kotlinx.coroutines.flow.Flow

interface IAsteroidRepo {
    suspend fun refreshAsteroids()
    suspend fun getTodayAsteroids(): Flow<AsteroidResponse>
    suspend fun getWeekAsteroids(): Flow<AsteroidResponse>
    suspend fun getAllAsteroids(): Flow<AsteroidResponse>
    suspend fun deleteAsteroids()
    suspend fun refreshPicture(): PictureResponse
}