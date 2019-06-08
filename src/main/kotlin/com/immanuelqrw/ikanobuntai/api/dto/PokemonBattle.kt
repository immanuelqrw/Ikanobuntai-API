package com.immanuelqrw.ikanobuntai.api.dto

import com.immanuelqrw.ikanobuntai.api.entity.BattleType
import com.immanuelqrw.ikanobuntai.api.entity.TierTitle
import java.time.LocalDateTime

data class PokemonBattle(
    val type: BattleType,
    val defendingTierTitle: TierTitle?,
    val defender: String,
    val defenderTeam: String,
    val challenger: String,
    val challengerTeam: String,
    val winner: String?,
    val league: String?,
    val foughtOn: LocalDateTime
)
