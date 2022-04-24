package com.example.mapper

import com.example.local.HeroEntity
import com.example.model.Hero
import com.example.model.HeroDetails
import com.example.remote.dto.HeroApi
import com.example.utils.mapUrl

fun HeroApi.toEntity(): HeroEntity {
    return HeroEntity(
        id = this.id,
        imageUrl =  this.imageUrl.mapUrl(),
        roles = this.roles,
        name = this.name,
        attribute = this.attribute,
        attackRange = this.attackRange,
        attackRate = this.attackRate,
        baseAgi = this.baseAgi,
        attackType = this.attackType,
        baseArmor = this.baseArmor,
        baseHealth = this.baseHealth,
        baseHealthRegen = this.baseHealthRegen,
        baseInt = this.baseInt,
        baseMana = this.baseMana,
        baseManaRegen = this.baseManaRegen,
        baseStr = this.baseStr,
        moveSpeed = this.moveSpeed
    )
}

fun HeroEntity.toHero(): Hero {
    return Hero(
        id = this.id,
        image_url = this.imageUrl,
        name = this.name
    )
}

fun HeroEntity.toHeroDetails(): HeroDetails {
    return HeroDetails(
        id = this.id,
        imageUrl =  this.imageUrl,
        roles = this.roles,
        name = this.name,
        attribute = this.attribute,
        attackRange = this.attackRange,
        attackRate = this.attackRate,
        baseAgi = this.baseAgi,
        attackType = this.attackType,
        baseArmor = this.baseArmor,
        baseHealth = this.baseHealth,
        baseHealthRegen = this.baseHealthRegen,
        baseInt = this.baseInt,
        baseMana = this.baseMana,
        baseManaRegen = this.baseManaRegen,
        baseStr = this.baseStr,
        moveSpeed = this.moveSpeed
    )
}
