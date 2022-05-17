package com.udacity.asteroidradar.model

import android.app.Application
import androidx.lifecycle.*
import com.udacity.asteroidradar.data.PictureOfDay
import com.udacity.asteroidradar.database.getAsteroidDatabase
import com.udacity.asteroidradar.repo.AsteroidRepo
import kotlinx.coroutines.launch

enum class PictureApiStatus {
    LOADING, ERROR, DONE;
}

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var _picture = MutableLiveData<PictureOfDay>()
    val picture: LiveData<PictureOfDay> = _picture

    private var _pictureStatus = MutableLiveData<PictureApiStatus>()
    val pictureStatus: LiveData<PictureApiStatus> = _pictureStatus

    private val asteroidDatabase = getAsteroidDatabase(application)
    private val asteroidRepo = AsteroidRepo(asteroidDatabase)

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

    val asteroids = asteroidRepo.asteroids
}