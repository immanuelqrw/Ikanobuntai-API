package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity
import com.immanuelqrw.ikanobuntai.api.setOfNotNull

data class PokemonGeneration(
    val pokemon: Pokemon,
    val generation: Generation,
    val mainType: Type,
    val subType: Type?,
    val stage: Int = 1,
    val baseStatTotal: Int,
    val hpBaseStat: Int,
    val attackBaseStat: Int,
    val defenseBaseStat: Int,
    val specialAttackBaseStat: Int,
    val specialDefenseBaseStat: Int,
    val speedBaseStat: Int,
    val ability: Ability,
    val alternateAbility: Ability?,
    val hiddenAbility: Ability?,
    val spriteUri: String
) : BaseUniqueEntity() {

    val types: Set<Type> by lazy {
        setOfNotNull(mainType, subType)
    }

}
