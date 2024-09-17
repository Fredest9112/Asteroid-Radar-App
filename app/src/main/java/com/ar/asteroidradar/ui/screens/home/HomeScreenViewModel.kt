package com.ar.asteroidradar.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ar.asteroidradar.domain.entities.PictureOfDay
import com.ar.asteroidradar.domain.entities.asDomainEntity
import com.ar.asteroidradar.domain.repo.IAsteroidRepo
import com.ar.asteroidradar.utils.Constants.PICTURE_OF_DAY_MOCK
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
            _pictureOfDay.value = asteroidRepo.refreshPicture().asDomainEntity()
        }
    }
}