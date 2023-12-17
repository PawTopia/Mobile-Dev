package com.example.pawtopia.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.pawtopia.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "onboarding_pref")

class DataStoreRepositoryImpl(context: Context) : DataStoreRepository {

    private object PrefrencesKey {
        val onboardingKey = booleanPreferencesKey(name = "onboarding_key")
    }

    private val dataStore = context.dataStore

    override suspend fun saveOnboardingState(isCompleted: Boolean) {
        dataStore.edit { pref ->
            pref[PrefrencesKey.onboardingKey] = isCompleted
        }
    }

    override fun readOnboardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) emit(emptyPreferences())
                else throw exception
            }
            .map { pref ->
                pref[PrefrencesKey.onboardingKey] ?: false
            }
    }
}