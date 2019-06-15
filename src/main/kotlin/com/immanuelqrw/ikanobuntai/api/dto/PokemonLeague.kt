package com.immanuelqrw.ikanobuntai.api.dto

import com.immanuelqrw.ikanobuntai.api.entity.Format
import com.immanuelqrw.ikanobuntai.api.entity.LeagueType
import com.immanuelqrw.ikanobuntai.api.entity.Stage
import com.immanuelqrw.ikanobuntai.api.entity.Tier

data class PokemonLeague(
    val name: String,
    val type: LeagueType,
    val stage: Stage,
    val tier: Tier,
    val stdDev: Int,
    val kFactor: Int,
    val prizeMin: Int,
    val formats: Collection<Format>,
    val trainers: Collection<String>
)
