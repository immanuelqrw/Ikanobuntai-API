package com.immanuelqrw.ikanobuntai.api.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.immanuelqrw.core.entity.BaseUniqueEntity
import com.immanuelqrw.ikanobuntai.api.dto.output.LeaguePokemon as LeaguePokemonOutput
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "LeaguePokemon")
data class LeaguePokemon(

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "trainerPokemonId", referencedColumnName = "id", nullable = false)
    val trainerPokemon: TrainerPokemon,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "leagueId", referencedColumnName = "id", nullable = false)
    val league: League

) : BaseUniqueEntity() {

    val output: LeaguePokemonOutput
        get() {
            return LeaguePokemonOutput(
                trainerPokemon = trainerPokemon.output,
                leagueName = league.name
            )
        }

}
