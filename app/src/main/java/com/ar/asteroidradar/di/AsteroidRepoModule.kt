package com.ar.asteroidradar.di

import com.ar.asteroidradar.database.AsteroidDatabase
import com.ar.asteroidradar.repo.IAsteroidRepo
import com.ar.asteroidradar.repo.impl.AsteroidRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AsteroidRepoModule {

    @Provides
    @Singleton
    fun provideAsteroidRepo(database: AsteroidDatabase): IAsteroidRepo {
        return AsteroidRepo(database = database)
    }
}