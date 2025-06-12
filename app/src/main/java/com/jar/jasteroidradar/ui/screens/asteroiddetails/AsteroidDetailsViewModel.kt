package com.jar.jasteroidradar.ui.screens.asteroiddetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jar.jasteroidradar.data.database.AsteroidDB
import com.jar.jasteroidradar.domain.exceptions.AsteroidResponse
import com.jar.jasteroidradar.domain.repo.IAsteroidRepo
import com.jar.jasteroidradar.domain.states.AsteroidDataState
import com.jar.jasteroidradar.utils.Constants.ASTEROID_DB_MOCK
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AsteroidDetailsViewModel @Inject constructor(
    private val asteroidRepo: IAsteroidRepo
) : ViewModel() {

    private var _asteroidDataState = MutableStateFlow(value = AsteroidDataState.LOADING)
    val asteroidDataState: StateFlow<AsteroidDataState> = _asteroidDataState

    private var _asteroid = MutableStateFlow(value = ASTEROID_DB_MOCK)
    val asteroid: StateFlow<AsteroidDB> = _asteroid

    fun getAsteroidById(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            asteroidRepo.getAsteroidById(id = id).collect {
                when (it) {
                    is AsteroidResponse.AsteroidSuccess -> {
                        _asteroidDataState.value = AsteroidDataState.COMPLETED
                        _asteroid.value = it.asteroid
                    }

                    is AsteroidResponse.AsteroidsError -> {
                        _asteroidDataState.value = AsteroidDataState.ERROR
                    }
                    else -> Unit
                }
            }
        }
    }
}