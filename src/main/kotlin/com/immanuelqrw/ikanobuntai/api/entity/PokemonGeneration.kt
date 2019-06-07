package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity
import com.immanuelqrw.ikanobuntai.api.setOfNotNull
import javax.persistence.CascadeType
import javax.persistence.Embedded
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

    @Enumerated
    val generation: Generation,

    @Enumerated
    val mainType: Type,

    @Enumerated
    val subType: Type?,

    val stage: Int = 1,

    @Embedded
    val baseStat: BaseStat,

    @Embedded
    val ability: PokemonAbility,

    val spriteUri: String

) : BaseUniqueEntity() {

    val types: Set<Type> by lazy {
        setOfNotNull(mainType, subType)
    }

}
