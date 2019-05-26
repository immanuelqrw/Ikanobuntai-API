package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity

data class Configuration(
    val name: String,
    val value: String,
    val type: String,
    val tier: Tier
) : BaseUniqueEntity()
