package com.jar.jasteroidradar.data.repoImpl

import android.util.Log
import com.jar.jasteroidradar.data.api.NetWork
import com.jar.jasteroidradar.data.models.asDatabaseModel
import com.jar.jasteroidradar.data.api.parseAsteroidsJsonResult
import com.jar.jasteroidradar.utils.Date
import com.jar.jasteroidradar.data.database.AsteroidDatabase
import com.jar.jasteroidradar.domain.exceptions.AsteroidResponse
import com.jar.jasteroidradar.domain.exceptions.PictureResponse
import com.jar.jasteroidradar.domain.repo.IAsteroidRepo
import com.jar.jasteroidradar.utils.Constants.API_KEY
import com.jar.jasteroidradar.utils.Constants.PICTURE_OF_DAY_REMOTE_MOCK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
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
                Log.i("error refreshing asteroids", "${e.message}")
            }
        }
    }

    override suspend fun getTodayAsteroids(): Flow<AsteroidResponse> {
        return database.asteroidDao.getTodayAsteroids(Date.currentTime)
            .catch { exception ->
                AsteroidResponse.AsteroidsError(exception = exception)
            }
            .map { asteroids ->
                AsteroidResponse.AsteroidsSuccess(asteroids = asteroids)
            }
    }

    override suspend fun getWeekAsteroids(): Flow<AsteroidResponse> {
        return database.asteroidDao.getWeekAsteroids(Date.oneWeekAgo, Date.currentTime)
            .catch { exception ->
                AsteroidResponse.AsteroidsError(exception = exception)
            }
            .map { asteroids ->
                AsteroidResponse.AsteroidsSuccess(asteroids = asteroids)
            }
    }

    override suspend fun getAllAsteroids(): Flow<AsteroidResponse> {
        return database.asteroidDao.getAsteroids()
            .catch { exception ->
                AsteroidResponse.AsteroidsError(exception = exception)
            }
            .map { asteroids ->
                AsteroidResponse.AsteroidsSuccess(asteroids = asteroids)
            }
    }

    override suspend fun deleteAsteroids(){
        withContext(Dispatchers.IO){
            try {
                database.asteroidDao.deleteOldAsteroids(Date.twoWeeksAgo, Date.oneWeekAgo)
            } catch (e: Exception){
                Log.i("error deleting asteroids","${e.message}")
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
                PictureResponse.PictureError(exception)
            }
        }
    }
}