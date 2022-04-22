package com.example.remote

import com.example.remote.dto.HeroApi
import retrofit2.http.GET

interface DotaApi {
    @GET("api/heroStats")
    suspend fun getChampions() : List<HeroApi>
}