package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity

data class Trainer(
    val name: String,
    val trainerUser: TrainerUser,
    val tier: Tier,
    val rank: Rank,
    val rating: Int = 1000
) : BaseUniqueEntity()
