package com.example.model

data class HeroDetails(
    val id: Int = 0,
    val imageUrl: String = "",
    val roles: List<String> = emptyList(),
    val name: String = "",
    val attribute: String = "",
    val attackRange: Float = 0f,
    val attackRate: Float = 0f,
    val attackType: String = "",
    val baseAgi: Float = 0f,
    val baseArmor: Float = 0f,
    val baseHealth: Float = 0f,
    val baseHealthRegen: Float = 0f,
    val baseInt: Float = 0f,
    val baseMana: Float = 0f,
    val baseManaRegen: Float = 0f,
    val baseStr: Float = 0f,
    val moveSpeed: Float = 0f
)
