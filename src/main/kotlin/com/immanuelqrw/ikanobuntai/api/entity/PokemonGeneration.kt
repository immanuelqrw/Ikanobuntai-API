package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity
import com.immanuelqrw.ikanobuntai.api.setOfNotNull
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "`PokemonGeneration`")
data class PokemonGeneration(

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`pokemonId`", referencedColumnName = "`id`")
    val pokemon: Pokemon,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`generationId`", referencedColumnName = "`id`")
    val generation: Generation,

    @Enumerated
    val mainType: Type,

    @Enumerated
    val subType: Type?,

    val stage: Int = 1,

    val baseStatTotal: Int,

    val hpBaseStat: Int,

    val attackBaseStat: Int,

    val defenseBaseStat: Int,

    val specialAttackBaseStat: Int,

    val specialDefenseBaseStat: Int,

    val speedBaseStat: Int,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`abilityId`", referencedColumnName = "`id`")
    val ability: Ability,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`alternateAbilityId`", referencedColumnName = "`id`")
    val alternateAbility: Ability?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`hiddenAbilityId`", referencedColumnName = "`id`")
    val hiddenAbility: Ability?,

    val spriteUri: String

) : BaseUniqueEntity() {

    val types: Set<Type> by lazy {
        setOfNotNull(mainType, subType)
    }

}
