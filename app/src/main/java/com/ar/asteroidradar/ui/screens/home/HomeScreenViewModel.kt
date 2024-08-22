package com.ar.asteroidradar.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ar.asteroidradar.data.Constants.PICTURE_OF_DAY_MOCK
import com.ar.asteroidradar.data.PictureOfDay
import com.ar.asteroidradar.repo.IAsteroidRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val asteroidRepo: IAsteroidRepo): ViewModel() {

    private var _pictureOfDay = MutableStateFlow(value = PICTURE_OF_DAY_MOCK)
    val pictureOfDay: StateFlow<PictureOfDay> = _pictureOfDay

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _pictureOfDay.value = asteroidRepo.refreshPicture()
        }
    }
}