package com.example.repository

import com.example.model.Hero
import com.example.utils.Resource
import kotlinx.coroutines.flow.Flow


interface MainScreenRepository {
    suspend fun getHeroesList(isUpdateNeeded: Boolean, query: String): Flow<Resource<List<Hero>>>
}