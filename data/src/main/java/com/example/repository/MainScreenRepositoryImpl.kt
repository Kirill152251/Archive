package com.example.repository

import com.example.local.HeroesDao
import com.example.mapper.toEntity
import com.example.mapper.toHero
import com.example.model.Hero
import com.example.remote.DotaApi
import com.example.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainScreenRepositoryImpl @Inject constructor(
    private val api: DotaApi,
    private val dao: HeroesDao
) : MainScreenRepository {
    override suspend fun getHeroesList(isUpdateNeeded: Boolean): Flow<Resource<List<Hero>>> {
        return flow {
            emit(Resource.Loading(true))
            val isDbEmpty = dao.getHeroes().isEmpty()
            if (isDbEmpty || isUpdateNeeded) {
            try {
                val remoteHeroesList = api.getChampions()
                dao.insertHeroes(heroes = remoteHeroesList.map { it.toEntity() })
                emit(
                    Resource.Success(
                        data = dao.getHeroes().map { it.toHero() }
                    )
                )
                emit(Resource.Loading(false))
            } catch (e: Exception) {
                emit(Resource.Loading(false))
                emit(Resource.Error(e.toString()))
            }
            } else {
                emit(Resource.Success(
                    data = dao.getHeroes().map { it.toHero() }
                ))
                emit(Resource.Loading(false))
            }
        }.flowOn(Dispatchers.IO)
    }
}