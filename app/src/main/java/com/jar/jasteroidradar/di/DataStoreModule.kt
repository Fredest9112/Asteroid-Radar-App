package com.jar.jasteroidradar.di

import android.content.Context
import com.jar.jasteroidradar.domain.repo.IDataStoreRepo
import com.jar.jasteroidradar.data.repoImpl.DataStoreRepo
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