package com.immanuelqrw.ikanobuntai.api.dto

import com.immanuelqrw.ikanobuntai.api.entity.BattleType
import com.immanuelqrw.ikanobuntai.api.entity.Rank
import java.time.LocalDateTime

data class PokemonBattle(
    val type: BattleType,
    val defender: String,
    val challenger: String,
    val winner: String?,
    val rank: Rank,
    val value: Int,
    val foughtOn: LocalDateTime
)
