package com.example.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.utils.PROFILE_DATASTORE
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PROFILE_DATASTORE)

class ProfileRepositoryImpl(private val context: Context) : ProfileRepository {
    companion object {
        val FIRST_NAME = stringPreferencesKey("first")
        val LAST_NAME = stringPreferencesKey("last")
        val NICKNAME = stringPreferencesKey("nick")
    }

    override suspend fun saveFirstName(firstName: String) {
        context.dataStore.edit { profile ->
            profile[FIRST_NAME] = firstName
        }
    }

    override suspend fun saveLastName(lastName: String) {
        context.dataStore.edit { profile ->
            profile[LAST_NAME] = lastName
        }
    }

    override suspend fun saveNickname(nickname: String) {
        context.dataStore.edit { profile ->
            profile[NICKNAME] = nickname
        }
    }

    override suspend fun getNickname(): String {
        val profileData = context.dataStore.data.first()
        return profileData[NICKNAME] ?: ""
    }

    override suspend fun getFirstName(): String {
        val profileData = context.dataStore.data.first()
        return profileData[FIRST_NAME] ?: ""
    }

    override suspend fun getLastName(): String {
        val profileData = context.dataStore.data.first()
        return profileData[LAST_NAME] ?: ""
    }
}