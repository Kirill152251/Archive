package com.example.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mapper.StringListConverter

@Database(
    entities = [HeroEntity::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(StringListConverter::class)
abstract class AppDataBase: RoomDatabase() {

    abstract fun heroesDao(): HeroesDao
}