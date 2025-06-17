package com.jar.jasteroidradar.data.api

import com.jar.jasteroidradar.data.models.PictureOfDayRemote
import com.jar.jasteroidradar.utils.Constants.ASTEROIDS_DATA
import com.jar.jasteroidradar.utils.Constants.PICTURE_DAY
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AsteroidService {
    @GET(PICTURE_DAY)
    fun getPictureOfDayAsync(): Deferred<Response<PictureOfDayRemote>>

    @GET(ASTEROIDS_DATA)
    fun getAsteroidsAsync(
        @Query("START_DATE") startDate: String,
        @Query("END_DATE") endDate: String,
        @Query("API_KEY") apiKey: String
    ): Deferred<String>
}
