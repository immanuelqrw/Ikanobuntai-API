package com.immanuelqrw.ikanobuntai.api.dto

import java.time.LocalDateTime

data class PokemonBattle(
    val defender: String,
    val challenger: String,
    val winner: String?,
    val rank: String,
    val value: Int,
    val foughtOn: LocalDateTime
)
