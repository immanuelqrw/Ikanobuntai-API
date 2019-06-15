package com.immanuelqrw.ikanobuntai.api.dto

import com.immanuelqrw.ikanobuntai.api.entity.BattleType
import com.immanuelqrw.ikanobuntai.api.entity.TierTitle
import java.time.LocalDateTime

data class PlannedBattle(
    val type: BattleType,
    val defendingTierTitle: TierTitle? = null,
    val defender: String,
    val challenger: String,
    val league: String,
    val toBeFoughtOn: LocalDateTime
)
