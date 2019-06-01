package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

// ? Should I validate that a move is possible
// ! Add check that no moves are repeated
@Entity
@Table(name = "`TrainerPokemon`")
data class TrainerPokemon(

    val name: String?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`pokemonGenerationId`", referencedColumnName = "`id`")
    val pokemonGeneration: PokemonGeneration,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`trainerId`", referencedColumnName = "`id`")
    val trainer: Trainer,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`tierId`", referencedColumnName = "`id`")
    val nature: Nature,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`abilityId`", referencedColumnName = "`id`")
    val ability: Ability,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`individualValueId`", referencedColumnName = "`id`")
    val individualValue: IndividualValue,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`efforValueId`", referencedColumnName = "`id`")
    val effortValue: EffortValue,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`move1Id`", referencedColumnName = "`id`")
    val move1: Move,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`move2Id`", referencedColumnName = "`id`")
    val move2: Move?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`move3Id`", referencedColumnName = "`id`")
    val move3: Move?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`move4Id`", referencedColumnName = "`id`")
    val move4: Move?,

    val happiness: Int = 255,

    val isShiny: Boolean = false,

    val level: Int = 100,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`itemId`", referencedColumnName = "`id`")
    val item: Item?

) : BaseUniqueEntity()
