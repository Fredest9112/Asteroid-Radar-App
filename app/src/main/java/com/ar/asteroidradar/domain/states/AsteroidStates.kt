package com.ar.asteroidradar.domain.states

import com.ar.asteroidradar.utils.Constants.ALL_TIME_STATE
import com.ar.asteroidradar.utils.Constants.TODAY_TIME_STATE
import com.ar.asteroidradar.utils.Constants.WEEK_TIME_STATE

enum class AsteroidDataState {
    LOADING,
    COMPLETED,
    ERROR
}

enum class AsteroidTimeState(val dateState: String) {
    TODAY(TODAY_TIME_STATE),
    WEEK(WEEK_TIME_STATE),
    ALL(ALL_TIME_STATE);

    companion object {
        fun fromString(value: String): AsteroidTimeState {
            return values().find { it.dateState == value } ?: TODAY
        }
    }
}