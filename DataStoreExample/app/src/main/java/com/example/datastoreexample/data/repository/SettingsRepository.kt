package com.example.datastoreexample.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.datastoreexample.data.model.UserSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsRepository(
    //ovde je dataStore
    private val dataStore: DataStore<Preferences>
) {
    //ovde je kako se mapiraju kljucevi iz dataStore-a tamo gde ih cuvamo
    //to je praka ne bitno sto nije bas isto ime kao u UserSettings
    private object Keys {
        val USERNAME = stringPreferencesKey("username")
        val BOLD_TEXT = booleanPreferencesKey("bold_text")
        val FONT_SIZE = intPreferencesKey("font_size")
        val ALL_NAMES = stringPreferencesKey("all_names")
    }
    //ovde ide sta iz baze ide u koji element UserSettings-a
    val settingsFlow: Flow<UserSettings> = dataStore.data
        .map { preferences ->
            //ide lista svih imena jer se pamti vise stvari ne jedna
            val namesString = preferences[Keys.ALL_NAMES] ?: ""
            UserSettings(
                username = preferences[Keys.USERNAME] ?: "",
                //ovde se one menja da se izbace zarezi ako ima i to
                allNames = if (namesString.isEmpty()) emptyList() else namesString.split(","),
                boldText = preferences[Keys.BOLD_TEXT] ?: false,
                fontSize = preferences[Keys.FONT_SIZE] ?: 16
            )
        }
    //sve ove funkcije su unutar njih sa lambda funkcijom koja menja odgovarajući parametar
    suspend fun updateUsername(username: String) {
        Log.v("Tag", username)
        dataStore.edit { preferences ->
            preferences[Keys.USERNAME] = username
        }
    }

    suspend fun updateBoldText(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[Keys.BOLD_TEXT] = enabled
        }
    }

    suspend fun updateFontSize(size: Int) {
        dataStore.edit { preferences ->
            preferences[Keys.FONT_SIZE] = size
        }
    }
    suspend fun addNameToList(newName: String) {
        dataStore.edit { prefs ->
            val current = prefs[Keys.ALL_NAMES] ?: ""
            //ovako ispod se menja lista stringova
            prefs[Keys.ALL_NAMES] = if (current.isEmpty()) newName else "$current,$newName"
        }
    }

    suspend fun selectName(name: String) {
        dataStore.edit { prefs ->
            prefs[Keys.USERNAME] = name
        }
    }
}