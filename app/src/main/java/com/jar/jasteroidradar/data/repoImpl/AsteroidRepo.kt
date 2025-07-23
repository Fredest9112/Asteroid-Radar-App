package com.jar.jasteroidradar.data.repoImpl

import android.util.Log
import com.jar.jasteroidradar.data.api.AsteroidService
import com.jar.jasteroidradar.data.api.parseAsteroidsJsonResult
import com.jar.jasteroidradar.data.database.AsteroidDB
import com.jar.jasteroidradar.data.database.AsteroidDao
import com.jar.jasteroidradar.data.models.PictureOfDayRemote
import com.jar.jasteroidradar.data.models.asDatabaseModel
import com.jar.jasteroidradar.domain.exceptions.Result
import com.jar.jasteroidradar.domain.repo.IAsteroidRepo
import com.jar.jasteroidradar.utils.Constants.API_KEY
import com.jar.jasteroidradar.utils.Constants.PICTURE_OF_DAY_REMOTE_MOCK
import com.jar.jasteroidradar.utils.Date
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AsteroidRepo @Inject constructor(
    private val asteroidDao: AsteroidDao,
    private val asteroidService: AsteroidService
): IAsteroidRepo {

    override suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {
            try {
                val response = asteroidService.getAsteroidsAsync(
                    Date.currentTime, Date.oneWeekAgo,
                    API_KEY
                ).await()
                val asteroidsList = parseAsteroidsJsonResult(JSONObject(response))
                asteroidDao.insertAll(*asteroidsList.asDatabaseModel())
            } catch (e: Exception) {
                Log.i("error refreshing asteroids", "${e.message}")
            }
        }

    }

    override fun getTodayAsteroids(): Flow<Result<List<AsteroidDB>>> {
        return asteroidDao.getTodayAsteroids(Date.currentTime)
            .map { Result.Success(data = it) }
            .catch { Result.Error("Database error retrieving local data: ${it.localizedMessage}", null) }
    }

    override fun getWeekAsteroids(): Flow<Result<List<AsteroidDB>>> {
        return asteroidDao.getWeekAsteroids(Date.oneWeekAgo, Date.currentTime)
            .map { Result.Success(data = it) }
            .catch { Result.Error("Database error retrieving local data: ${it.localizedMessage}", null) }
    }

    override fun getAllAsteroids(): Flow<Result<List<AsteroidDB>>> {
        return asteroidDao.getAsteroids()
            .map { Result.Success(data = it) }
            .catch { Result.Error("Database error retrieving local data: ${it.localizedMessage}", null) }
    }

    override fun getAsteroidById(id: Long): Flow<Result<AsteroidDB>> {
        return asteroidDao.getAsteroid(id = id)
            .map { Result.Success(data = it) }
            .catch { Result.Error("Database error retrieving local data: ${it.localizedMessage}", null) }
    }

    override suspend fun deleteAsteroids() {
        withContext(Dispatchers.IO){
            try {
                asteroidDao.deleteOldAsteroids(Date.twoWeeksAgo, Date.oneWeekAgo)
            } catch (e: Exception){
                Log.i("error deleting asteroids","${e.message}")
            }
        }
    }

    override fun refreshPicture(): Flow<Result<PictureOfDayRemote>> {
        return flow {
            val result: Result<PictureOfDayRemote> = try {
                val response = asteroidService.getPictureOfDayAsync().await()
                val pictureOfDayRemote = response.body() ?: PICTURE_OF_DAY_REMOTE_MOCK
                Result.Success(data = pictureOfDayRemote)
            } catch (exception: Exception) {
                Result.Error("Error receiving pictures: ${exception.localizedMessage}", null)
            }
            emit(result)
        }
    }
}