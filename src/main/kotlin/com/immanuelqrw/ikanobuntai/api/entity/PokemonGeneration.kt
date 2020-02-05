package com.immanuelqrw.ikanobuntai.api.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.immanuelqrw.core.entity.BaseUniqueEntity
import com.immanuelqrw.ikanobuntai.api.setOfNotNull
import javax.persistence.*

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "PokemonGeneration")
data class PokemonGeneration(

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "pokemonId", referencedColumnName = "id", nullable = false)
    val pokemon: Pokemon,

    @Enumerated
    @Column(name = "generation", nullable = false)
    val generation: Generation,

    @Enumerated
    @Column(name = "mainType", nullable = false)
    val mainType: Type,

    @Enumerated
    @Column(name = "subType")
    val subType: Type?,

    @Column(name = "stage", nullable = false)
    val stage: Int = 1,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "prevolvedPokemonId", referencedColumnName = "id")
    val prevolvedPokemon: Pokemon?,

    @Embedded
    @AttributeOverrides(value = [
        AttributeOverride(name = "total", column = Column(name = "baseStatTotal", nullable = false)),
        AttributeOverride(name = "hp", column = Column(name = "hpBaseStat", nullable = false)),
        AttributeOverride(name = "attack", column = Column(name = "attackBaseStat", nullable = false)),
        AttributeOverride(name = "defense", column = Column(name = "defenseBaseStat", nullable = false)),
        AttributeOverride(name = "specialAttack", column = Column(name = "specialAttackBaseStat", nullable = false)),
        AttributeOverride(name = "specialDefense", column = Column(name = "specialDefenseBaseStat", nullable = false)),
        AttributeOverride(name = "speed", column = Column(name = "speedBaseStat", nullable = false))
    ])
    val baseStat: BaseStat,

    @Embedded
    @AttributeOverrides(value = [
        AttributeOverride(name = "main", column = Column(name = "abilityId", nullable = false)),
        AttributeOverride(name = "alternate", column = Column(name = "alternateAbilityId", nullable = false)),
        AttributeOverride(name = "hidden", column = Column(name = "hiddenAbilityId", nullable = false))
    ])
    val ability: PokemonAbility,

    @Column(name = "spriteUri", nullable = false)
    val spriteUri: String

) : BaseUniqueEntity() {

    val types: Set<Type> by lazy {
        setOfNotNull(mainType, subType)
    }

}
