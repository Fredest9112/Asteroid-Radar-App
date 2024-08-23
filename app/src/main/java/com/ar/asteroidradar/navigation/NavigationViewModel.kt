package com.ar.asteroidradar.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ar.asteroidradar.repo.IDataStoreRepo
import com.ar.asteroidradar.utils.OnBoardingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val dataStoreRepo: IDataStoreRepo
) : ViewModel() {
    private val _onBoardingState = MutableStateFlow(OnBoardingState.LOADING)
    val onBoardingState: StateFlow<OnBoardingState> = _onBoardingState

    init {
        viewModelScope.launch {
            val isCompleted = dataStoreRepo.readOnBoardingState().first()
            _onBoardingState.value = if (isCompleted) {
                OnBoardingState.COMPLETED
            } else {
                OnBoardingState.NOT_COMPLETED
            }
        }
    }
}