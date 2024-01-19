package com.ar.asteroidradar.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.ar.asteroidradar.data.Constants.ASTEROIDS_DATA
import com.ar.asteroidradar.data.Constants.BASE_URL
import com.ar.asteroidradar.data.Constants.PICTURE_DAY
import com.ar.asteroidradar.data.PictureOfDay
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface AsteroidService {
    @GET(PICTURE_DAY)
    fun getPictureOfDayAsync(): Deferred<Response<PictureOfDay>>

    @GET(ASTEROIDS_DATA)
    fun getAsteroidsAsync(
        @Query("START_DATE") startDate: String,
        @Query("END_DATE") endDate: String,
        @Query("API_KEY") apiKey: String
    ): Deferred<String>
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

object NetWork {

    private val interceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder().apply {
        this.addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
    }.build()

    private val retrofitAsteroids = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .client(client)
        .build()

    val asteroidsData: AsteroidService = retrofitAsteroids.create(AsteroidService::class.java)
}