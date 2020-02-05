package com.immanuelqrw.ikanobuntai.api.dto.input

import com.immanuelqrw.ikanobuntai.api.entity.Nature

// ? Should I validate that a move is possible
// ! Add check that no moves are repeated
data class TrainerPokemon(

    val name: String?,

    val pokemonGeneration: PokemonGeneration,

    val trainer: Trainer,

    val nature: Nature,

    val ability: Ability,

    val individualValue: IndividualValue,

    val effortValue: EffortValue,

    val move1: Move,

    val move2: Move?,

    val move3: Move?,

    val move4: Move?,

    val happiness: Int = 255,

    val isShiny: Boolean = false,

    val level: Int = 100,

    val item: Item?

)
