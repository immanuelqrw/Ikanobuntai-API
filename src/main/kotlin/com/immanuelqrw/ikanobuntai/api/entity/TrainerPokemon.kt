package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity

data class TrainerPokemon(
    val pokemon: Pokemon,
    val trainer: Trainer,
    val nature: Nature,
    val ability: Ability,
    val individualValue: IndividualValue,
    val move1: Move,
    val mode2: Move?,
    val mode3: Move?,
    val mode4: Move?
) : BaseUniqueEntity()
