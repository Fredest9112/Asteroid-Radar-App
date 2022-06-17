package com.ar.asteroidradar.work

import android.app.Application
import androidx.work.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class AsteroidApplication: Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    private fun delayedInit(){
        applicationScope.launch {
            setupRecurringWork()
        }
    }

    private fun setupRecurringWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(true)
            .setRequiresDeviceIdle(true)
            .build()

        val refreshAsteroidsRequest = PeriodicWorkRequestBuilder<RefreshWorker>(1, TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()

        val deleteAsteroidsRequest = PeriodicWorkRequestBuilder<DeleteWorker>(14, TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance().enqueueUniquePeriodicWork(
            RefreshWorker.WORK_REFRESH_ASTEROIDS,
            ExistingPeriodicWorkPolicy.KEEP,
            refreshAsteroidsRequest
        )

        WorkManager.getInstance().enqueueUniquePeriodicWork(
            DeleteWorker.WORK_DELETE_ASTEROIDS,
            ExistingPeriodicWorkPolicy.KEEP,
            deleteAsteroidsRequest
        )
    }

    override fun onCreate() {
        super.onCreate()
        delayedInit()
    }
}