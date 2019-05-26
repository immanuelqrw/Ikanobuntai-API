package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity

data class PokemonTrainer(
    val pokemon: Pokemon,
    val trainer: Trainer,
    val nature: Nature,
    val ability: Ability,
    val individualValue: IndividualValue
) : BaseUniqueEntity()
