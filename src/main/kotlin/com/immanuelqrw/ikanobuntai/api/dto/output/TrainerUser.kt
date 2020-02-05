package com.immanuelqrw.ikanobuntai.api.dto.output

data class TrainerUser(

    val name: String,

    val email: String,

    val passwordHash: String,

    val passwordSalt: String,

    val oAuthId: String

)
