package com.bosch.composewithkotlin20.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.bosch.composewithkotlin20.domain.manger.LocalUserManager
import com.bosch.composewithkotlin20.util.Constants
import com.bosch.composewithkotlin20.util.Constants.USER_SETTING
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserMangerImp(private val context: Context): LocalUserManager {
	override suspend fun saveAppEntry() {
	context.dataStore.edit { settings ->
		settings[PrefrencesKeys.APP_ENTRY] = true
	}
	}
	
	override fun readAppEntry(): Flow<Boolean> {
		return context.dataStore.data.map { preferences ->
			preferences[PrefrencesKeys.APP_ENTRY]?: false
		}
	}
}
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTING)
private object PrefrencesKeys {
	val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)
}