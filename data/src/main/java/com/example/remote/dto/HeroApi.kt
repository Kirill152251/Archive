package com.example.remote.dto

import com.google.gson.annotations.SerializedName

data class HeroApi(
    @SerializedName("icon")
    val icon_url: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("img")
    val image_url: String,

    @SerializedName("localized_name")
    val name: String
)
