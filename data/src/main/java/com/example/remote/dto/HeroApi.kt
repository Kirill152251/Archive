package com.example.remote.dto

import com.google.gson.annotations.SerializedName

data class HeroApi(
    @SerializedName("id")
    val id: Int,

    @SerializedName("img")
    val imageUrl: String,

    @SerializedName("roles")
    val roles: List<String>,

    @SerializedName("localized_name")
    val name: String,

    @SerializedName("primary_attr")
    val attribute: String,

    @SerializedName("attack_range")
    val attackRange: Float,

    @SerializedName("attack_rate")
    val attackRate: Float,

    @SerializedName("attack_type")
    val attackType: String,

    @SerializedName("base_agi")
    val baseAgi: Float,

    @SerializedName("base_armor")
    val baseArmor: Float,

    @SerializedName("base_health")
    val baseHealth: Float,

    @SerializedName("base_health_regen")
    val baseHealthRegen: Float,

    @SerializedName("base_int")
    val baseInt: Float,

    @SerializedName("base_mana")
    val baseMana: Float,

    @SerializedName("base_mana_regen")
    val baseManaRegen: Float,

    @SerializedName("base_str")
    val baseStr: Float,

    @SerializedName("move_speed")
    val moveSpeed: Float
)
