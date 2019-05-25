package com.immanuelqrw.ikanobuntai.api.elo

data class League(
    val name: String,
    // - Consider changing standard value
    val kFactor: Int = 32
)
