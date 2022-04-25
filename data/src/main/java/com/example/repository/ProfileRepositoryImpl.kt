package com.example.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.utils.PROFILE_DATASTORE
import com.google.gson.annotations.Since
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PROFILE_DATASTORE)

@Singleton
class ProfileRepositoryImpl @Inject constructor(private val context: Context) : ProfileRepository {

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
        Log.i("TAG", profileData[NICKNAME].toString())
        return profileData[NICKNAME] ?: ""
    }

    override suspend fun getFirstName(): String {
        val profileData = context.dataStore.data.first()
        Log.i("TAG", profileData[FIRST_NAME].toString())
        return profileData[FIRST_NAME] ?: ""
    }

    override suspend fun getLastName(): String {
        val profileData = context.dataStore.data.first()
        Log.i("TAG", profileData[LAST_NAME].toString())
        return profileData[LAST_NAME] ?: ""
    }
}