package com.ar.asteroidradar.ui.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ar.asteroidradar.domain.repo.IDataStoreRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(private val dataStoreRepo: IDataStoreRepo): ViewModel() {
    fun saveOnBoardingState(complete: Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepo.saveOnBoardingState(completed = complete)
        }
    }
}