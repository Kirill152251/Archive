package com.example.mapper

import com.example.local.HeroEntity
import com.example.model.Hero
import com.example.remote.dto.HeroApi
import com.example.utils.mapUrl

fun HeroApi.toEntity(): HeroEntity {
    return HeroEntity(
        icon_url, id, image_url.mapUrl(), name
    )
}

fun HeroApi.toHero(): Hero {
    return Hero(
        icon_url, id, image_url.mapUrl(), name
    )
}

fun HeroEntity.toHero(): Hero {
    return Hero(
        icon_url, id, image_url, name
    )
}
