package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity

data class Title(
    val name: String,
    val tier: Tier
) : BaseUniqueEntity()
