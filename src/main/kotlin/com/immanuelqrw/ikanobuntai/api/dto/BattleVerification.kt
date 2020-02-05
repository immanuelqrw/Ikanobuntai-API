package com.immanuelqrw.ikanobuntai.api.dto

import com.immanuelqrw.ikanobuntai.api.entity.BattleType
import com.immanuelqrw.ikanobuntai.api.entity.TierTitle

data class BattleVerification(

    val defender: String,

    val challenger: String,

    val battleType: BattleType,

    val league: String,

    val tierTitle: TierTitle?

)
