package com.udacity.asteroidradar.main

import android.util.Log
import androidx.lifecycle.*
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.Constants.API_KEY
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.NetWork
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import kotlinx.coroutines.launch
import org.json.JSONObject

enum class PictureApiStatus { LOADING, ERROR, DONE }

class MainViewModel : ViewModel() {

    private var _pictureStatus = MutableLiveData<PictureApiStatus>()
    val pictureStatus: LiveData<PictureApiStatus> = _pictureStatus

    private var _picture = MutableLiveData<PictureOfDay?>()
    val picture: LiveData<PictureOfDay?> = _picture

    init {
        getPictureOfDay()
        getAsteroidData()
    }

    private fun getAsteroidData() {
        viewModelScope.launch {
            try {
                val response = NetWork.asteroids.getAsteroidsAsync(
                    "2022-05-16", "2022-05-09",
                    API_KEY
                )
                val asteroidData = response.await().body()
                if (response.await().isSuccessful && asteroidData != null) {
                    Log.i("data", "${parseAsteroidsJsonResult(JSONObject(asteroidData))}")
                }
            } catch (e: Exception) {
                Log.i("error", "${e.printStackTrace()}")
            }
        }
    }

    private fun getPictureOfDay() {
        viewModelScope.launch {
            _pictureStatus.value = PictureApiStatus.LOADING
            try {
                val response = NetWork.dailyPicture.getPictureOfDayAsync()
                val picture = response.await().body()
                if (response.await().isSuccessful && picture != null) {
                    _picture.value = picture
                    _pictureStatus.value = PictureApiStatus.DONE
                }
            } catch (e: Exception) {
                _picture.value = PictureOfDay("", "", "")
                _pictureStatus.value = PictureApiStatus.ERROR
            }
        }
    }
}