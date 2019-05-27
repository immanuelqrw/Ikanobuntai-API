package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity

data class League(
    val name: String,
    val format: LeagueFormat,
    val stage: Stage,
    val tier: Tier,
    val elo: Elo
) : BaseUniqueEntity()
