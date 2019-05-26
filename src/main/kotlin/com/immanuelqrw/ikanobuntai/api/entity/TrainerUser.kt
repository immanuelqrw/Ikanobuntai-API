package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity

data class TrainerUser(
    val name: String,
    val email: String,
    val passwordHash: String,
    val passwordSalt: String,
    val oAuthId: String
) : BaseUniqueEntity()
