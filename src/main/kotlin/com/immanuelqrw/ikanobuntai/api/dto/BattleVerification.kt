package com.immanuelqrw.ikanobuntai.api.dto

import com.immanuelqrw.ikanobuntai.api.entity.BattleType
import com.immanuelqrw.ikanobuntai.api.entity.TierTitle
import java.util.UUID

data class BattleVerification(
    val defenderId: UUID,
    val challengerId: UUID,
    val battleType: BattleType,
    val leagueId: UUID,
    val tierTitle: TierTitle?
)
