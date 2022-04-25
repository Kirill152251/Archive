package com.example.repository

interface ProfileRepository {
    suspend fun saveFirstName(firstName: String)
    suspend fun saveLastName(lastName: String)
    suspend fun saveNickname(nickname: String)
    suspend fun getNickname(): String
    suspend fun getFirstName(): String
    suspend fun getLastName(): String
}