package com.ar.asteroidradar.utils

import com.ar.asteroidradar.data.database.AsteroidDB
import com.ar.asteroidradar.data.models.AsteroidRemote
import com.ar.asteroidradar.data.models.PictureOfDayRemote
import com.ar.asteroidradar.domain.entities.PictureOfDay

object Constants {
    //DataStorePreferences
    const val DATA_STORE_PREFS = "asteroid_preferences"
    const val DATA_STORE_ONBOARDING_STATUS = "on_boarding_status"

    //App constants
    const val ASTEROID_DB_NAME = "asteroids"
    const val ASTEROID_ID_KEY = "asteroidId"
    const val LAST_PAGE_HORIZONTAL_PAGER = 2
    const val API_QUERY_DATE_FORMAT = "YYYY-MM-dd"
    const val DEFAULT_END_DATE_DAYS = 7
    const val DEFAULT_DELETE_DAYS = 30
    const val BASE_URL = "https://api.nasa.gov/"
    const val PICTURE_DAY = "planetary/apod?api_key=wT9VJl9RYcLFrQDTvlwGTu24BkSHy3l8SU7eAmIi"
    const val ASTEROIDS_DATA = "neo/rest/v1/feed"
    const val API_KEY = "wT9VJl9RYcLFrQDTvlwGTu24BkSHy3l8SU7eAmIi"
    const val TODAY_TIME_STATE = "Today"
    const val WEEK_TIME_STATE = "Week"
    const val ALL_TIME_STATE = "All"
    val DATE_OPTIONS = listOf(TODAY_TIME_STATE,WEEK_TIME_STATE,ALL_TIME_STATE)
    val PICTURE_OF_DAY_MOCK = PictureOfDay(
        copyright = "copyright",
        date = "??-??-??",
        explanation = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        url = "",
        title = "????"
    )
    val PICTURE_OF_DAY_REMOTE_MOCK = PictureOfDayRemote(
        copyright = "copyright",
        date = "??-??-??",
        explanation = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        serviceVersion = "",
        mediaType = "image",
        title = "?????????",
        url = ""
    )
    val ASTEROIDS_DB_MOCK = listOf(
        AsteroidDB(
            id = 12345L,
            codename = "codename",
            closeApproachDate = "approachDate",
            absoluteMagnitude = 0.0,
            estimatedDiameter = 0.0,
            relativeVelocity = 0.0,
            distanceFromEarth = 0.0,
            isPotentiallyHazardous = true
        ),
        AsteroidDB(
            id = 12345L,
            codename = "codename",
            closeApproachDate = "approachDate",
            absoluteMagnitude = 0.0,
            estimatedDiameter = 0.0,
            relativeVelocity = 0.0,
            distanceFromEarth = 0.0,
            isPotentiallyHazardous = true
        ),
        AsteroidDB(
            id = 12345L,
            codename = "codename",
            closeApproachDate = "approachDate",
            absoluteMagnitude = 0.0,
            estimatedDiameter = 0.0,
            relativeVelocity = 0.0,
            distanceFromEarth = 0.0,
            isPotentiallyHazardous = true
        )
    )

    val ASTEROIDS_MOCK = listOf(
        AsteroidRemote(
            id = 1L,
            codename = "????",
            closeApproachDate = "????",
            absoluteMagnitude = 0.0,
            estimatedDiameter = 0.0,
            relativeVelocity = 0.0,
            distanceFromEarth = 0.0,
            isPotentiallyHazardous = false
        ),
        AsteroidRemote(
            id = 2L,
            codename = "????",
            closeApproachDate = "????",
            absoluteMagnitude = 0.0,
            estimatedDiameter = 0.0,
            relativeVelocity = 0.0,
            distanceFromEarth = 0.0,
            isPotentiallyHazardous = false
        ),
        AsteroidRemote(
            id = 3L,
            codename = "????",
            closeApproachDate = "????",
            absoluteMagnitude = 0.0,
            estimatedDiameter = 0.0,
            relativeVelocity = 0.0,
            distanceFromEarth = 0.0,
            isPotentiallyHazardous = false
        )
    )
}