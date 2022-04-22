package com.example.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [HeroEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase: RoomDatabase() {

    abstract fun heroesDao(): HeroesDao
}