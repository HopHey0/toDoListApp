package com.example.pract_7_8.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.example.pract_7_8.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class UserPreferencesRepositoryImpl(
    private val dataStore: DataStore<Preferences>
): UserPreferencesRepository {
    private companion object {
        val IS_COMPLETED_HAS_COLOR = booleanPreferencesKey("completed_color_is_green")
    }

    override val isCompletedGreen: Flow<Boolean> = dataStore.data
        .catch {
            if (it is IOException) {
                emit(emptyPreferences())
            } else throw it
        }
        .map { prefs -> prefs[IS_COMPLETED_HAS_COLOR] ?: true }

    override suspend fun setCompletedColorIsGreen(isGreen: Boolean) {
        dataStore.edit { prefs ->
            prefs[IS_COMPLETED_HAS_COLOR] = isGreen
        }
    }
}