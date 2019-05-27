package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity

data class LeagueFormat(
    val league: League,
    val format: Format
) : BaseUniqueEntity()
