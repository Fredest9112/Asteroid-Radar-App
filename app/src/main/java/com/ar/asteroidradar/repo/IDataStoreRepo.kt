package com.ar.asteroidradar.repo

import kotlinx.coroutines.flow.Flow

interface IDataStoreRepo {
    suspend fun saveOnBoardingState(completed: Boolean)
    fun readOnBoardingState(): Flow<Boolean>
}