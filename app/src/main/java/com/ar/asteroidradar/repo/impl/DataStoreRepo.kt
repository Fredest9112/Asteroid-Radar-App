package com.ar.asteroidradar.repo.impl

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.ar.asteroidradar.repo.IDataStoreRepo
import com.ar.asteroidradar.utils.Constants.DATA_STORE_ONBOARDING_STATUS
import com.ar.asteroidradar.utils.Constants.DATA_STORE_PREFS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class DataStoreRepo(context: Context) : IDataStoreRepo {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_PREFS)

    private object PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(name = DATA_STORE_ONBOARDING_STATUS)
    }

    private val dataStore = context.dataStore

    override suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { preference ->
            preference[PreferencesKey.onBoardingKey] = completed
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                print("Exception: ${exception.printStackTrace()}")
                emit(emptyPreferences())
            }
            .map {
                val onBoardingState = it[PreferencesKey.onBoardingKey] ?: false
                onBoardingState
            }
    }
}