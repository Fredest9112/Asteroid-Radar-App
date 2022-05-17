package com.udacity.asteroidradar.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.api.NetWork
import com.udacity.asteroidradar.api.asDatabaseModel
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.data.*
import com.udacity.asteroidradar.data.Constants.API_KEY
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.database.asDomainModel
import com.udacity.asteroidradar.model.PictureApiStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AsteroidRepo(private val database: AsteroidDatabase) {

    val asteroids: LiveData<List<Asteroid>> =
        Transformations.map(database.asteroidDao.getAsteroids()) {
            it.asDomainModel()
        }

    suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {
            try {
                val response = NetWork.asteroidsData.getAsteroidsAsync(
                    Date.currentTime, Date.sevenDaysAgo,
                    API_KEY
                ).await()
                val asteroidsList = parseAsteroidsJsonResult(JSONObject(response))
                database.asteroidDao.insertAll(*asteroidsList.asDatabaseModel())
            } catch (e: Exception) {
                Log.i("error on asteroids", "${e.printStackTrace()}")
            }
        }
    }

    suspend fun refreshPicture(): PictureOfDay {
        var pictureOfDay = PictureOfDay("", "", "")
        withContext(Dispatchers.IO) {
            try {
                val response = NetWork.asteroidsData.getPictureOfDayAsync().await()
                pictureOfDay = response.body() ?: PictureOfDay("", "", "")
            } catch (e: Exception) {
                Log.i("error on picture", "${e.printStackTrace()}")
            }
        }
        return pictureOfDay
    }
}