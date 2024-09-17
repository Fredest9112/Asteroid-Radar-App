package com.ar.asteroidradar.domain.repo

import com.ar.asteroidradar.domain.exceptions.DatastoreResponse
import kotlinx.coroutines.flow.Flow

interface IDataStoreRepo {
    suspend fun saveOnBoardingState(completed: Boolean): Result<Unit>
    fun readOnBoardingState(): Flow<DatastoreResponse>
}