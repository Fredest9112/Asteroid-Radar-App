package com.jar.jasteroidradar.di

import com.jar.jasteroidradar.data.database.AsteroidDatabase
import com.jar.jasteroidradar.domain.repo.IAsteroidRepo
import com.jar.jasteroidradar.data.repoImpl.AsteroidRepo
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