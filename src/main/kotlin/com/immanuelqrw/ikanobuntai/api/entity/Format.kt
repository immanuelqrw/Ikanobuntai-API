package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity

data class Format(
    val name: String,
    val description: String,
    val hasRuleset: Boolean = false
) : BaseUniqueEntity()
