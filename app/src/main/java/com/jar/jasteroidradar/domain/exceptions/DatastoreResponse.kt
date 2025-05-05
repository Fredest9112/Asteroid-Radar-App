package com.jar.jasteroidradar.domain.exceptions

import kotlinx.coroutines.flow.Flow

sealed class DatastoreResponse {
    data class Success (val isOnboardingComplete: Flow<Boolean>): DatastoreResponse()
    data class Error(val exception: Throwable): DatastoreResponse()
}