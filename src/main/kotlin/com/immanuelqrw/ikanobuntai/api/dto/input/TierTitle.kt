package com.immanuelqrw.ikanobuntai.api.dto.input

import com.immanuelqrw.ikanobuntai.api.entity.Tier
import com.immanuelqrw.ikanobuntai.api.entity.Title

data class TierTitle(

    val tier: Tier,

    val title: Title,

    val prizeName: String

)
