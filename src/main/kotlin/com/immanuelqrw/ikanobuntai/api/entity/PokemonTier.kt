package com.immanuelqrw.ikanobuntai.api.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.immanuelqrw.core.entity.BaseUniqueEntity
import com.immanuelqrw.ikanobuntai.api.dto.output.PokemonTier as PokemonTierOutput
import javax.persistence.*

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "PokemonTier")
data class PokemonTier(

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "pokemonIs", referencedColumnName = "id", nullable = false)
    val pokemon: Pokemon,

    @Enumerated
    @Column(name = "generation", nullable = false)
    val generation: Generation,

    @Enumerated
    @Column(name = "tier", nullable = false)
    val tier: Tier

) : BaseUniqueEntity() {
    
    val output: PokemonTierOutput
        get() {
            return PokemonTierOutput(
                pokemon = pokemon.output,
                generation = generation,
                tier = tier
            )
        }

}
