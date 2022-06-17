package com.ar.asteroidradar.repo

import android.util.Log
import com.ar.asteroidradar.api.NetWork
import com.ar.asteroidradar.api.asDatabaseModel
import com.ar.asteroidradar.api.parseAsteroidsJsonResult
import com.ar.asteroidradar.data.Constants.API_KEY
import com.ar.asteroidradar.data.Date
import com.ar.asteroidradar.data.PictureOfDay
import com.ar.asteroidradar.database.AsteroidDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AsteroidRepo(private val database: AsteroidDatabase) {

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