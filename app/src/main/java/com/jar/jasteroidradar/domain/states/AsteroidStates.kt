package com.jar.jasteroidradar.domain.states

import com.jar.jasteroidradar.utils.Constants.ALL_TIME_STATE
import com.jar.jasteroidradar.utils.Constants.TODAY_TIME_STATE
import com.jar.jasteroidradar.utils.Constants.WEEK_TIME_STATE

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