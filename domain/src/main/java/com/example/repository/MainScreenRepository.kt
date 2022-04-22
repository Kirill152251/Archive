package com.example.repository

import androidx.lifecycle.LiveData
import com.example.model.Hero
import com.example.utils.Resource
import kotlinx.coroutines.flow.Flow


interface MainScreenRepository {
    suspend fun getHeroesList(isUpdateNeeded: Boolean): Flow<Resource<List<Hero>>>
}