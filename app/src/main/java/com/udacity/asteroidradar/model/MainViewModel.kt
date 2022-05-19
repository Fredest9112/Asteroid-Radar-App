package com.udacity.asteroidradar.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.udacity.asteroidradar.data.Asteroid
import com.udacity.asteroidradar.data.Date
import com.udacity.asteroidradar.data.PictureOfDay
import com.udacity.asteroidradar.database.asDomainModel
import com.udacity.asteroidradar.database.getAsteroidDatabase
import com.udacity.asteroidradar.repo.AsteroidRepo
import kotlinx.coroutines.launch

enum class PictureApiStatus {
    LOADING, ERROR, DONE;
}

enum class AsteroidStatus {
    DAY, WEEK, ALL
}

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val asteroidDatabase = getAsteroidDatabase(application)
    private val asteroidRepo = AsteroidRepo(asteroidDatabase)

    private var _picture = MutableLiveData<PictureOfDay>()
    val picture: LiveData<PictureOfDay> = _picture

    private var _pictureStatus = MutableLiveData<PictureApiStatus>()
    val pictureStatus: LiveData<PictureApiStatus> = _pictureStatus

    private var _goToAsteroidDetails = MutableLiveData<Asteroid?>()
    val goToAsteroidDetails: LiveData<Asteroid?> = _goToAsteroidDetails

    var asteroids: LiveData<List<Asteroid>> = Transformations.map(
        asteroidDatabase.asteroidDao.getAsteroids()
    ) {
        it.asDomainModel()
    }


    init {
        _pictureStatus.value = PictureApiStatus.LOADING
        viewModelScope.launch {
            _picture.value = asteroidRepo.refreshPicture()
            checkPictureStatus()
            asteroidRepo.refreshAsteroids()
        }
    }

    private fun checkPictureStatus() {
        if (_picture.value?.url.isNullOrEmpty()) {
            _pictureStatus.value = PictureApiStatus.ERROR
        } else {
            _pictureStatus.value = PictureApiStatus.DONE
        }
    }

    fun displayAsteroidDetails(asteroid: Asteroid) {
        _goToAsteroidDetails.value = asteroid
    }

    fun displayAsteroidComplete() {
        _goToAsteroidDetails.value = null
    }

    fun updateAsteroidStatus(status: AsteroidStatus) {
        when (status) {
            AsteroidStatus.DAY -> {
                asteroids = Transformations.map(
                    asteroidDatabase.asteroidDao.getTodayAsteroids(Date.currentTime)
                ) {
                    it.asDomainModel()
                }
            }
            AsteroidStatus.WEEK -> {
                asteroids = Transformations.map(
                    asteroidDatabase.asteroidDao.getWeekAsteroids(
                        Date.sevenDaysAgo,
                        Date.currentTime
                    )
                ) {
                    it.asDomainModel()
                }
            }
            else -> {
                asteroids = Transformations.map(
                    asteroidDatabase.asteroidDao.getAsteroids()
                ) {
                    it.asDomainModel()
                }
            }
        }
    }
}