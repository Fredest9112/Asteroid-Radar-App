package com.jar.jasteroidradar.domain.repo

import com.jar.jasteroidradar.domain.exceptions.DatastoreResponse
import kotlinx.coroutines.flow.Flow

interface IDataStoreRepo {
    suspend fun saveOnBoardingState(completed: Boolean): Result<Unit>
    fun readOnBoardingState(): Flow<DatastoreResponse>
}