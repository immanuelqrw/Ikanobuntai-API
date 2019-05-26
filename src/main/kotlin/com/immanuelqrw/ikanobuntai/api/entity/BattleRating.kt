package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity

data class BattleRating(
    val defenderTitle: Title,
    val challengerTitle: Title,
    val league: League,
    val value: Int
) : BaseUniqueEntity()
