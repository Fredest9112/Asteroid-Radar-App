package com.ar.asteroidradar.di

import android.content.Context
import com.ar.asteroidradar.domain.repo.IDataStoreRepo
import com.ar.asteroidradar.data.repoImpl.DataStoreRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStoreRepo(@ApplicationContext context: Context): IDataStoreRepo {
        return DataStoreRepo(context)
    }
}