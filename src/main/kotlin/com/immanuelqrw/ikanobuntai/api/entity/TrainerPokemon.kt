package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity

data class TrainerPokemon(
    val name: String?,
    val pokemonGeneration: PokemonGeneration,
    val trainer: Trainer,
    val nature: Nature,
    val ability: Ability,
    val individualValue: IndividualValue,
    val effortValue: EffortValue,
    val move1: Move,
    val mode2: Move?,
    val mode3: Move?,
    val mode4: Move?,
    val happiness: Int = 255,
    val isShiny: Boolean = false,
    val level: Int = 100,
    val item: Item?
) : BaseUniqueEntity()
