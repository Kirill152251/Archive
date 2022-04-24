package com.example.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HeroesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeroes(heroes: List<HeroEntity>)

    @Query("SELECT * FROM HEROES_DB")
    fun getHeroes(): List<HeroEntity>

    @Query("DELETE FROM HEROES_DB")
    fun clearDb()

    @Query(
        """
            SELECT * FROM HEROES_DB
            WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%'
        """
    )
    suspend fun searchHero(query: String): List<HeroEntity>

    @Query("SELECT * FROM HEROES_DB WHERE (id) LIKE (:id)")
    fun getHeroDetails(id: Int): HeroEntity
}