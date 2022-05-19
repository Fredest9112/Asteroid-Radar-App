package com.udacity.asteroidradar.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.database.getAsteroidDatabase
import com.udacity.asteroidradar.repo.AsteroidRepo
import retrofit2.HttpException

class RefreshWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshWorker"
    }

    override suspend fun doWork(): Result {
        val database = getAsteroidDatabase(applicationContext)
        val repository = AsteroidRepo(database)
        return try{
            repository.refreshAsteroids()
            Result.success()
        } catch(e: HttpException){
            Result.retry()
        }
    }
}