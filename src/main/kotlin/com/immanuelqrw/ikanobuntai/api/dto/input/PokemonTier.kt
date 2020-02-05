package com.immanuelqrw.ikanobuntai.api.dto.input

import com.immanuelqrw.ikanobuntai.api.entity.Generation
import com.immanuelqrw.ikanobuntai.api.entity.Tier

data class PokemonTier(

    val pokemon: Pokemon,

    val generation: Generation,

    val tier: Tier

)
