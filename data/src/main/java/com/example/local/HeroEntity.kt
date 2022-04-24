package com.example.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.utils.HEROES_TABLE_NAME
import com.google.gson.annotations.SerializedName

@Entity(tableName = HEROES_TABLE_NAME)
data class HeroEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val imageUrl: String,
    val roles: List<String>,
    val name: String,
    val attribute: String,
    val attackRange: Float,
    val attackRate: Float,
    val attackType: String,
    val baseAgi: Float,
    val baseArmor: Float,
    val baseHealth: Float,
    val baseHealthRegen: Float,
    val baseInt: Float,
    val baseMana: Float,
    val baseManaRegen: Float,
    val baseStr: Float,
    val moveSpeed: Float
)
