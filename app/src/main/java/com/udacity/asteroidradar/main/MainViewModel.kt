package com.udacity.asteroidradar.main

import androidx.lifecycle.*
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.NetWork
import kotlinx.coroutines.launch

enum class PictureApiStatus { LOADING, ERROR, DONE }

class MainViewModel : ViewModel() {

    private var _pictureStatus = MutableLiveData<PictureApiStatus>()
    val pictureStatus: LiveData<PictureApiStatus> = _pictureStatus

    private var _picture = MutableLiveData<PictureOfDay?>()
    val picture: LiveData<PictureOfDay?> = _picture

    init {
        getPictureOfDay()
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