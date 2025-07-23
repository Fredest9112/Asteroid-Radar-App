package com.jar.jasteroidradar.data.repoImpl

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.jar.jasteroidradar.domain.exceptions.Result
import com.jar.jasteroidradar.domain.repo.IDataStoreRepo
import com.jar.jasteroidradar.utils.Constants.DATA_STORE_ONBOARDING_STATUS
import com.jar.jasteroidradar.utils.Constants.DATA_STORE_PREFS
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
        try {
            dataStore.edit { preference ->
                preference[PreferencesKey.onBoardingKey] = completed
            }
            Result.Success(data = Unit)
        } catch (exception: Exception) {
            Result.Error("error on saveOnBoardingState: ${exception.localizedMessage}", null)
        }
    }

    override fun readOnBoardingState(): Flow<Result<Boolean>> {
        return dataStore.data
            .map { Result.Success(data = it[PreferencesKey.onBoardingKey] ?: false) }
            .catch { Result.Error("error on saveOnBoardingState: ${it.localizedMessage}", null) }
    }
}