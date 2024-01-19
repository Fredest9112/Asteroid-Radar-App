package com.ar.asteroidradar.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ar.asteroidradar.database.getAsteroidDatabase
import com.ar.asteroidradar.repo.AsteroidRepo
import retrofit2.HttpException

class DeleteWorker(appContext: Context, params: WorkerParameters): CoroutineWorker(appContext, params) {

    companion object{
        const val WORK_DELETE_ASTEROIDS = "DeleteAsteroidsWorker"
    }

    override suspend fun doWork(): Result {
        val database = getAsteroidDatabase(applicationContext)
        val repository = AsteroidRepo(database)
        return try {
            repository.deleteAsteroids()
            Result.success()
        } catch (e: HttpException){
            Result.retry()
        }
    }
}