package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity
import java.time.LocalDateTime

data class Battle(
    val defender: Trainer,
    val challenger: Trainer,
    val winner: Trainer,
    val rank: Rank,
    val value: Int,
    val foughtOn: LocalDateTime
) : BaseUniqueEntity()
