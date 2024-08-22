package com.ar.asteroidradar.repo.impl

import android.util.Log
import com.ar.asteroidradar.api.NetWork
import com.ar.asteroidradar.api.asDatabaseModel
import com.ar.asteroidradar.api.parseAsteroidsJsonResult
import com.ar.asteroidradar.data.Constants.API_KEY
import com.ar.asteroidradar.data.Constants.PICTURE_OF_DAY_MOCK
import com.ar.asteroidradar.data.Date
import com.ar.asteroidradar.data.PictureOfDay
import com.ar.asteroidradar.database.AsteroidDatabase
import com.ar.asteroidradar.repo.IAsteroidRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AsteroidRepo(private val database: AsteroidDatabase): IAsteroidRepo {

    override suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {
            try {
                val response = NetWork.asteroidsData.getAsteroidsAsync(
                    Date.currentTime, Date.oneWeekAgo,
                    API_KEY
                ).await()
                val asteroidsList = parseAsteroidsJsonResult(JSONObject(response))
                database.asteroidDao.insertAll(*asteroidsList.asDatabaseModel())
            } catch (e: Exception) {
                Log.i("error on asteroids", "${e.printStackTrace()}")
            }
        }
    }

    override suspend fun deleteAsteroids(){
        withContext(Dispatchers.IO){
            try {
                database.asteroidDao.deleteOldAsteroids(Date.twoWeeksAgo, Date.oneWeekAgo)
            } catch (e: Exception){
                Log.i("error deleting asteroids","${e.printStackTrace()}")
            }
        }
    }

    override suspend fun refreshPicture(): PictureOfDay {
        var pictureOfDay = PICTURE_OF_DAY_MOCK
        withContext(Dispatchers.IO) {
            try {
                val response = NetWork.asteroidsData.getPictureOfDayAsync().await()
                pictureOfDay = response.body() ?: pictureOfDay
            } catch (e: Exception) {
                Log.i("error on picture", "$e")
            }
        }
        return pictureOfDay
    }
}