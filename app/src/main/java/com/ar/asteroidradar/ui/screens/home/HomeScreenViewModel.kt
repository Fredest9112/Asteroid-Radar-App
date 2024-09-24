package com.ar.asteroidradar.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ar.asteroidradar.domain.entities.PictureOfDay
import com.ar.asteroidradar.domain.entities.asDomainEntity
import com.ar.asteroidradar.domain.exceptions.PictureResponse
import com.ar.asteroidradar.domain.repo.IAsteroidRepo
import com.ar.asteroidradar.domain.states.PictureState
import com.ar.asteroidradar.utils.Constants.PICTURE_OF_DAY_MOCK
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val asteroidRepo: IAsteroidRepo): ViewModel() {

    private var _pictureOfDay = MutableStateFlow(value = PICTURE_OF_DAY_MOCK)
    val pictureOfDay: StateFlow<PictureOfDay> = _pictureOfDay

    private val _pictureState = MutableStateFlow(PictureState.LOADING)
    val pictureState: StateFlow<PictureState> = _pictureState

    private val _shouldShowHomeError = MutableStateFlow(Pair(false, ""))
    val shouldShowHomeError: StateFlow<Pair<Boolean, String>> = _shouldShowHomeError

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val deferredPicture = async { asteroidRepo.refreshPicture() }
                val deferredAsteroids = async { asteroidRepo.refreshAsteroids() }
                when (val pictureResponse = deferredPicture.await()){
                    is PictureResponse.PictureSuccess -> {
                        _pictureOfDay.value = pictureResponse.pictureOfDayRemote.asDomainEntity()
                        _pictureState.value = PictureState.COMPLETED
                    }
                    is PictureResponse.PictureError -> {
                        _pictureState.value = PictureState.ERROR
                        _shouldShowHomeError.value = Pair(true, pictureResponse.exception.message.toString())
                    }
                }
                deferredAsteroids.await()
            } catch (exception: Exception) {
                _pictureState.value = PictureState.ERROR
                _shouldShowHomeError.value = Pair(true, exception.message.toString())
            }
        }
    }
}