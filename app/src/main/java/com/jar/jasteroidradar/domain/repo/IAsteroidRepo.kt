package com.jar.jasteroidradar.domain.repo

import com.jar.jasteroidradar.data.database.AsteroidDB
import com.jar.jasteroidradar.data.models.PictureOfDayRemote
import com.jar.jasteroidradar.domain.exceptions.Result
import kotlinx.coroutines.flow.Flow

interface IAsteroidRepo {
    suspend fun refreshAsteroids()
    fun getTodayAsteroids(): Flow<Result<List<AsteroidDB>>>
    fun getWeekAsteroids(): Flow<Result<List<AsteroidDB>>>
    fun getAllAsteroids(): Flow<Result<List<AsteroidDB>>>
    suspend fun deleteAsteroids()
    fun refreshPicture(): Flow<Result<PictureOfDayRemote>>
    fun getAsteroidById(id: Long): Flow<Result<AsteroidDB>>
}