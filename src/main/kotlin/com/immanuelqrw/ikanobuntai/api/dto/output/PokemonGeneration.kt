package com.immanuelqrw.ikanobuntai.api.dto.output

import com.immanuelqrw.ikanobuntai.api.entity.Generation
import com.immanuelqrw.ikanobuntai.api.entity.Type

data class PokemonGeneration(

    val pokemon: Pokemon,

    val generation: Generation,

    val mainType: Type,

    val subType: Type?,

    val stage: Int,

    val prevolvedPokemon: Pokemon?,

    val baseStatTotal: Int,

    val hpBaseStat: Int,

    val attackBaseStat: Int,

    val defenseBaseStat: Int,

    val specialAttackBaseStat: Int,

    val specialDefenseBaseStat: Int,

    val speedBaseStat: Int,

    val mainAbilityName: String,

    val alternateAbilityName: String,

    val hiddenAbilityName: String,

    val spriteUri: String

)
