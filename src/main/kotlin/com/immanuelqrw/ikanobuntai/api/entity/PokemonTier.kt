package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "`PokemonTier`")
data class PokemonTier(

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`pokemonIs`", referencedColumnName = "`id`")
    val pokemon: Pokemon,

    @Enumerated
    val generation: Generation,

    @Enumerated
    val tier: Tier

) : BaseUniqueEntity()
