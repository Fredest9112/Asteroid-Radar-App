package com.ar.asteroidradar.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ar.asteroidradar.data.database.AsteroidDB
import com.ar.asteroidradar.domain.entities.PictureOfDay
import com.ar.asteroidradar.domain.entities.asDomainEntity
import com.ar.asteroidradar.domain.exceptions.AsteroidResponse
import com.ar.asteroidradar.domain.exceptions.PictureResponse
import com.ar.asteroidradar.domain.repo.IAsteroidRepo
import com.ar.asteroidradar.domain.states.AsteroidDataState
import com.ar.asteroidradar.domain.states.AsteroidTimeState
import com.ar.asteroidradar.domain.states.PictureState
import com.ar.asteroidradar.utils.Constants.ASTEROIDS_MOCK
import com.ar.asteroidradar.utils.Constants.PICTURE_OF_DAY_MOCK
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val asteroidRepo: IAsteroidRepo) :
    ViewModel() {

    private var _asteroidDataState = MutableStateFlow(value = AsteroidDataState.LOADING)
    val asteroidDataState : StateFlow<AsteroidDataState> = _asteroidDataState

    private var _asteroids = MutableStateFlow(value = ASTEROIDS_MOCK.asDomainEntity())
    val asteroids: StateFlow<List<AsteroidDB>> = _asteroids

    private var _pictureOfDay = MutableStateFlow(value = PICTURE_OF_DAY_MOCK)
    val pictureOfDay: StateFlow<PictureOfDay> = _pictureOfDay

    private val _pictureState = MutableStateFlow(PictureState.LOADING)
    val pictureState: StateFlow<PictureState> = _pictureState

    private val _shouldShowHomeError = MutableStateFlow(false to "")
    val shouldShowHomeError: StateFlow<Pair<Boolean, String>> = _shouldShowHomeError

    private val _selectedOption = MutableStateFlow(value = AsteroidTimeState.TODAY)
    val selectedOption: StateFlow<AsteroidTimeState> = _selectedOption

    init {
        viewModelScope.launch(Dispatchers.IO) {
            launch { getPictureOfTheDay() }
            launch { getAsteroidsOfTheDay() }
        }
    }

    private fun getAsteroidsOfTheDay() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val deferredAsteroids = async { asteroidRepo.refreshAsteroids() }
                deferredAsteroids.await()
                if(deferredAsteroids.isCompleted) {
                    asteroidRepo.getTodayAsteroids().collect {
                        fetchAsteroidData(it)
                    }
                }
            } catch (exception: Exception) {
                _shouldShowHomeError.value = true to exception.message.toString()
            }
        }
    }

    private fun getPictureOfTheDay() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val deferredPicture = async { asteroidRepo.refreshPicture() }
                when (val pictureResponse = deferredPicture.await()) {
                    is PictureResponse.PictureSuccess -> {
                        _pictureOfDay.value = pictureResponse.pictureOfDayRemote.asDomainEntity()
                        _pictureState.value = PictureState.COMPLETED
                    }
                    is PictureResponse.PictureError -> {
                        _pictureState.value = PictureState.ERROR
                        _shouldShowHomeError.value = true to pictureResponse.exception.message.toString()
                    }
                }
            } catch (exception: Exception) {
                _shouldShowHomeError.value = true to exception.message.toString()
            }
        }
    }

    fun onOptionSelected(timeOption: String) {
        _selectedOption.value = AsteroidTimeState.fromString(timeOption)
        _asteroidDataState.value = AsteroidDataState.LOADING
        viewModelScope.launch {
            when(_selectedOption.value){
                AsteroidTimeState.TODAY -> {
                    asteroidRepo.getTodayAsteroids().collect {
                        fetchAsteroidData(data = it)
                    }
                }
                AsteroidTimeState.WEEK -> {
                    asteroidRepo.getWeekAsteroids().collect {
                        fetchAsteroidData(data = it)
                    }
                }
                else -> {
                    asteroidRepo.getAllAsteroids().collect {
                        fetchAsteroidData(data = it)
                    }
                }
            }
        }
    }

    fun errorShown() {
        _shouldShowHomeError.value = false to ""
    }

    private fun fetchAsteroidData(data: AsteroidResponse) {
        when (data) {
            is AsteroidResponse.AsteroidsSuccess -> {
                _asteroidDataState.value = AsteroidDataState.COMPLETED
                _asteroids.value = data.asteroids.ifEmpty { ASTEROIDS_MOCK.asDomainEntity() }
            }

            is AsteroidResponse.AsteroidsError -> {
                _asteroidDataState.value = AsteroidDataState.ERROR
                _shouldShowHomeError.value = true to data.exception.message.toString()
            }
        }
    }
}