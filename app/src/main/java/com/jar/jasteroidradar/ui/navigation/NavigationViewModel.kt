package com.jar.jasteroidradar.ui.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jar.jasteroidradar.domain.exceptions.DatastoreResponse
import com.jar.jasteroidradar.domain.repo.IDataStoreRepo
import com.jar.jasteroidradar.domain.states.OnBoardingState
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

    private val _startDestination = MutableStateFlow<Screen>(Screen.Splash)
    val startDestination: StateFlow<Screen> = _startDestination

    private val _shouldShowError = MutableStateFlow(false to "")
    val shouldShowError: StateFlow<Pair<Boolean, String>> = _shouldShowError

    init {
        viewModelScope.launch {
            when (val datastoreResponse = dataStoreRepo.readOnBoardingState().first()) {
                is DatastoreResponse.Success -> {
                    if(datastoreResponse.isOnboardingComplete.first()) {
                        _startDestination.value = Screen.Home
                        _onBoardingState.value = OnBoardingState.COMPLETED
                    } else {
                        _startDestination.value = Screen.Welcome
                        _onBoardingState.value = OnBoardingState.NOT_COMPLETED
                    }
                }
                is DatastoreResponse.Error -> {
                    _startDestination.value = Screen.Home
                    _onBoardingState.value = OnBoardingState.NOT_COMPLETED
                    _shouldShowError.value = true to datastoreResponse.exception.message.toString()
                }
            }
        }
    }

    fun errorShown() {
        _shouldShowError.value = false to ""
    }
}
