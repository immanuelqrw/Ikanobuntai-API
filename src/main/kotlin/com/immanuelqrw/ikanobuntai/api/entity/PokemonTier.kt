package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity

data class PokemonTier(
    val pokemon: Pokemon,
    val generation: Generation,
    val tier: Tier
) : BaseUniqueEntity()
