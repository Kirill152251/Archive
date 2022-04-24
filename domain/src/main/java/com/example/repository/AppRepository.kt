package com.example.repository

import com.example.model.Hero
import com.example.model.HeroDetails
import com.example.utils.Resource
import kotlinx.coroutines.flow.Flow


interface AppRepository {
    suspend fun getHeroesList(isUpdateNeeded: Boolean, query: String): Flow<Resource<List<Hero>>>
    fun getHeroDetails(id: Int): Flow<HeroDetails>
}