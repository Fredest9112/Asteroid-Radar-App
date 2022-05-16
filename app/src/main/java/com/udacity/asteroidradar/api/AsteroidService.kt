package com.udacity.asteroidradar.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants.ASTEROIDS_DATA
import com.udacity.asteroidradar.Constants.BASE_URL
import com.udacity.asteroidradar.Constants.PICTURE_DAY
import com.udacity.asteroidradar.PictureOfDay
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface AsteroidService {
    @GET(PICTURE_DAY)
    fun getPictureOfDayAsync(): Deferred<Response<PictureOfDay>>

    @GET(ASTEROIDS_DATA)
    fun getAsteroidsAsync(
        @Query("START_DATE") startDate: String,
        @Query("END_DATE") endDate: String,
        @Query("API_KEY") apiKey: String
    ): Deferred<Response<String>>
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

object NetWork {
    private val retrofitPicture = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    private val retrofitAsteroids = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

    val dailyPicture: AsteroidService = retrofitPicture.create(AsteroidService::class.java)
    val asteroids: AsteroidService = retrofitAsteroids.create(AsteroidService::class.java)
}