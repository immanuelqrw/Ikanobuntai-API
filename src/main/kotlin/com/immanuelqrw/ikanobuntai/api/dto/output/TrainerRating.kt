package com.immanuelqrw.ikanobuntai.api.dto.output

import com.immanuelqrw.ikanobuntai.api.entity.Rank
import com.immanuelqrw.ikanobuntai.api.entity.Tier

data class TrainerRating(

    val trainerName: String,

    val tier: Tier,

    val rank: Rank,

    val elo: Int

)
