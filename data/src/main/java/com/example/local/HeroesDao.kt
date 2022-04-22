package com.example.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HeroesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeroes(heroes: List<HeroEntity>)

    @Query("select * from HEROES_DB")
    fun getHeroes(): List<HeroEntity>

    @Query("delete from HEROES_DB")
    fun clearDb()
}