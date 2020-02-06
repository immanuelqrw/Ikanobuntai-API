package com.immanuelqrw.ikanobuntai.api.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.immanuelqrw.core.entity.BaseUniqueEntity
import com.immanuelqrw.ikanobuntai.api.dto.output.TrainerPokemon as TrainerPokemonOutput
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

// ? Should I validate that a move is possible
// ! Add check that no moves are repeated
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "TrainerPokemon")
data class TrainerPokemon(

    @Column(name = "name")
    val name: String?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "pokemonGenerationId", referencedColumnName = "id", nullable = false)
    val pokemonGeneration: PokemonGeneration,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "trainerId", referencedColumnName = "id", nullable = false)
    val trainer: Trainer,

    @Column(name = "nature", nullable = false)
    val nature: Nature,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "abilityId", referencedColumnName = "id", nullable = false)
    val ability: Ability,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "individualValueId", referencedColumnName = "id", nullable = false)
    val individualValue: IndividualValue,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "effortValueId", referencedColumnName = "id", nullable = false)
    val effortValue: EffortValue,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "move1Id", referencedColumnName = "id", nullable = false)
    val move1: Move,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "move2Id", referencedColumnName = "id", nullable = false)
    val move2: Move?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "move3Id", referencedColumnName = "id", nullable = false)
    val move3: Move?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "move4Id", referencedColumnName = "id", nullable = false)
    val move4: Move?,

    @Column(name = "happiness", nullable = false)
    val happiness: Int = 255,

    @Column(name = "isShiny", nullable = false)
    val isShiny: Boolean = false,

    @Column(name = "level", nullable = false)
    val level: Int = 100,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "itemId", referencedColumnName = "id")
    val item: Item?

) : BaseUniqueEntity() {

    val output: TrainerPokemonOutput
        get() {
            return TrainerPokemonOutput(
                name = name,
                pokemonGeneration = pokemonGeneration.output,
                trainer = trainer.output,
                nature = nature,
                ability = ability.output,
                individualValue = individualValue.output,
                effortValue = effortValue.output,
                move1 = move1.output,
                move2 = move2?.output,
                move3 = move3?.output,
                move4 = move4?.output,
                happiness = happiness,
                isShiny = isShiny,
                level = level,
                item = item?.output
            )
        }

}
