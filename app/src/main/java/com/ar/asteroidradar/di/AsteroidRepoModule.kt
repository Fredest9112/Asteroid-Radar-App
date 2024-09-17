package com.ar.asteroidradar.di

import com.ar.asteroidradar.data.database.AsteroidDatabase
import com.ar.asteroidradar.domain.repo.IAsteroidRepo
import com.ar.asteroidradar.data.repoImpl.AsteroidRepo
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