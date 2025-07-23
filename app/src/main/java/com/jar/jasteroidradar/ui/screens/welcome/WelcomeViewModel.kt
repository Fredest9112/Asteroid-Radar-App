package com.jar.jasteroidradar.ui.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jar.jasteroidradar.domain.repo.IDataStoreRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(private val dataStoreRepo: IDataStoreRepo): ViewModel() {
    fun saveOnBoardingState(complete: Boolean){
        viewModelScope.launch {
            dataStoreRepo.saveOnBoardingState(completed = complete)
        }
    }
}