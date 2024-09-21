package com.ar.asteroidradar.data.repoImpl

import android.util.Log
import com.ar.asteroidradar.data.api.NetWork
import com.ar.asteroidradar.data.models.asDatabaseModel
import com.ar.asteroidradar.data.api.parseAsteroidsJsonResult
import com.ar.asteroidradar.utils.Date
import com.ar.asteroidradar.data.database.AsteroidDatabase
import com.ar.asteroidradar.domain.exceptions.PictureResponse
import com.ar.asteroidradar.domain.repo.IAsteroidRepo
import com.ar.asteroidradar.utils.Constants.API_KEY
import com.ar.asteroidradar.utils.Constants.PICTURE_OF_DAY_REMOTE_MOCK
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

    override suspend fun refreshPicture(): PictureResponse {
        var pictureOfDayRemote = PICTURE_OF_DAY_REMOTE_MOCK
        return withContext(Dispatchers.IO) {
            try {
                val response = NetWork.asteroidsData.getPictureOfDayAsync().await()
                pictureOfDayRemote = response.body() ?: pictureOfDayRemote
                PictureResponse.PictureSuccess(pictureOfDayRemote = pictureOfDayRemote)
            } catch (exception: Exception) {
                Log.i("error on picture", "${exception.printStackTrace()}")
                PictureResponse.PictureError(exception)
            }
        }
    }
}