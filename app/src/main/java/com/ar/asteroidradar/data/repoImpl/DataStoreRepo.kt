package com.ar.asteroidradar.data.repoImpl

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.ar.asteroidradar.domain.exceptions.DatastoreResponse
import com.ar.asteroidradar.domain.repo.IDataStoreRepo
import com.ar.asteroidradar.utils.Constants.DATA_STORE_ONBOARDING_STATUS
import com.ar.asteroidradar.utils.Constants.DATA_STORE_PREFS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class DataStoreRepo(context: Context) : IDataStoreRepo {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_PREFS)

    private object PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(name = DATA_STORE_ONBOARDING_STATUS)
    }

    private val dataStore = context.dataStore

    override suspend fun saveOnBoardingState(completed: Boolean): Result<Unit> {
        return try {
            dataStore.edit { preference ->
                preference[PreferencesKey.onBoardingKey] = completed
            }
            Result.success(Unit)
        } catch (exception: Exception) {
            Log.e("error on saveOnBoardingState","${exception.printStackTrace()}")
            Result.failure(exception)
        }
    }

    override fun readOnBoardingState(): Flow<DatastoreResponse> {
        return dataStore.data
            .catch { exception ->
                emit(emptyPreferences())
                Log.e("error on readOnBoardingState","${exception.printStackTrace()}")
                DatastoreResponse.Error(exception)
            }
            .map {
                val onBoardingState = it[PreferencesKey.onBoardingKey] ?: false
                DatastoreResponse.Success(isOnboardingComplete = flowOf(onBoardingState))
            }
    }
}