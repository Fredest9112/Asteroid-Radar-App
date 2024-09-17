package com.ar.asteroidradar.di

import android.content.Context
import androidx.room.Room
import com.ar.asteroidradar.data.database.AsteroidDatabase
import com.ar.asteroidradar.utils.Constants.ASTEROID_DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AsteroidDBModule {

    @Provides
    @Singleton
    fun provideAsteroidDB(@ApplicationContext context: Context): AsteroidDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = AsteroidDatabase::class.java,
            name = ASTEROID_DB_NAME
        ).build()
    }
}