package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity

data class Nature(
    val name: String,
    val dropStat: Stat,
    val riseStat: Stat
) : BaseUniqueEntity()
