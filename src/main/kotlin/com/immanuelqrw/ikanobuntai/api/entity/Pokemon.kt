package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity

data class Pokemon(
    val number: String,
    val name: String,
    val form: String?,
    val isLegendary: Boolean = false,
    val isMythical: Boolean = false,
    val isMega: Boolean = false,
    val prevolvedPokemon: Pokemon?
) : BaseUniqueEntity()
