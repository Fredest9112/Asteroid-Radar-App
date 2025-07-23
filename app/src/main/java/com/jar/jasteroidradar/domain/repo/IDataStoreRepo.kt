package com.jar.jasteroidradar.domain.repo

import com.jar.jasteroidradar.domain.exceptions.Result
import kotlinx.coroutines.flow.Flow

interface IDataStoreRepo {
    suspend fun saveOnBoardingState(completed: Boolean)
    fun readOnBoardingState(): Flow<Result<Boolean>>
}