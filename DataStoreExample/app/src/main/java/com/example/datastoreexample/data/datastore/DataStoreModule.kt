package com.example.datastoreexample.data.datastore

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
//datastore sa imenom ovo samo nauci nista vise
// ime je kao u android manifest pretpostavljam
val Context.settingsDataStore by preferencesDataStore(
    name = "user_settings"
)
