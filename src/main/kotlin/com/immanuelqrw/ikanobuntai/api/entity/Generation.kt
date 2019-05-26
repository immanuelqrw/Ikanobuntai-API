package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity

data class Generation(
    val number: Int,
    val region: String
) : BaseUniqueEntity()
