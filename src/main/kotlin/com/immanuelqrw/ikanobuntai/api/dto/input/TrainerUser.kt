package com.immanuelqrw.ikanobuntai.api.dto.input

data class TrainerUser(

    val name: String,

    val email: String,

    val passwordHash: String,

    val passwordSalt: String,

    val oAuthId: String

)
