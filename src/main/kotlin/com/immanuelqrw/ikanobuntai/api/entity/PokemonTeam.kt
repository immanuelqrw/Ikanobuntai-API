package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@Table(name = "`PokemonTeam`", uniqueConstraints = [UniqueConstraint(columnNames = ["`trainerTeamId`", "`trainerPokemonId`"])])
data class PokemonTeam(

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`trainerTeamId`", referencedColumnName = "`id`")
    val trainerTeam: TrainerTeam,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`trainerPokemonId`", referencedColumnName = "`id`")
    val trainerPokemon: TrainerPokemon

): BaseUniqueEntity()
