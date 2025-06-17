package com.jar.jasteroidradar.di

import com.jar.jasteroidradar.data.api.AsteroidService
import com.jar.jasteroidradar.data.database.AsteroidDao
import com.jar.jasteroidradar.data.repoImpl.AsteroidRepo
import com.jar.jasteroidradar.domain.repo.IAsteroidRepo
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
    fun provideAsteroidRepo(
        asteroidDao: AsteroidDao,
        asteroidService: AsteroidService
    ): IAsteroidRepo {
        return AsteroidRepo(
            asteroidDao = asteroidDao,
            asteroidService = asteroidService
        )
    }
}