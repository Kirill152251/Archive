package com.example.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.utils.HEROES_TABLE_NAME

@Entity(tableName = HEROES_TABLE_NAME)
data class HeroEntity(
    val icon_url: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val image_url: String,
    val name: String
)
