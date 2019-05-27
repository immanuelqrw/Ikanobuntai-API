package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity

data class Move(
    val name: String,
    val type: Type,
    val damage: Damage,
    val accuracy: Int?,
    val pp: Int,
    val effect: String?
) : BaseUniqueEntity()
