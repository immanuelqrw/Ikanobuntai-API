package com.immanuelqrw.ikanobuntai.api.dto

import com.immanuelqrw.ikanobuntai.api.entity.BattleType
import com.immanuelqrw.ikanobuntai.api.entity.TierTitle

data class BattleVerification(

    val defenderName: String,

    val challengerName: String,

    val battleType: BattleType,

    val leagueName: String,

    val tierTitle: TierTitle? = null

)
