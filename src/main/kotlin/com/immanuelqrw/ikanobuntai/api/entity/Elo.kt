package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity

data class Elo(
    val divider: Int,
    val k: Int
) : BaseUniqueEntity()
