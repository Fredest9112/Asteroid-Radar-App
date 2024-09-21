package com.ar.asteroidradar.ui.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ar.asteroidradar.domain.exceptions.DatastoreResponse
import com.ar.asteroidradar.domain.repo.IDataStoreRepo
import com.ar.asteroidradar.domain.states.OnBoardingState
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

    private val _startDestination = MutableStateFlow(Screen.Splash.route)
    val startDestination: StateFlow<String> = _startDestination

    private val _shouldShowError = MutableStateFlow(Pair(false, ""))
    val shouldShowError: StateFlow<Pair<Boolean, String>> = _shouldShowError

    init {
        viewModelScope.launch {
            when (val datastoreResponse = dataStoreRepo.readOnBoardingState().first()) {
                is DatastoreResponse.Success -> {
                    if(datastoreResponse.isOnboardingComplete.first()) {
                        _startDestination.value = Screen.Home.route
                        _onBoardingState.value = OnBoardingState.COMPLETED
                    } else {
                        _startDestination.value = Screen.Welcome.route
                        _onBoardingState.value = OnBoardingState.NOT_COMPLETED
                    }
                }
                is DatastoreResponse.Error -> {
                    _startDestination.value = Screen.Home.route
                    _onBoardingState.value = OnBoardingState.NOT_COMPLETED
                    _shouldShowError.value = Pair(true, datastoreResponse.exception.message.toString())
                }
            }
        }
    }
}
