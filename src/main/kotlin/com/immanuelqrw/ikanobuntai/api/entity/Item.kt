package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity

data class Item(
    val name: String,
    val effect: String
) : BaseUniqueEntity()
