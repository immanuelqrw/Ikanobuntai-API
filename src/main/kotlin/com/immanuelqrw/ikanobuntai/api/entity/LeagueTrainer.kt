package com.immanuelqrw.ikanobuntai.api.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.immanuelqrw.core.entity.BaseUniqueEntity
import com.immanuelqrw.ikanobuntai.api.dto.output.LeagueTrainer as LeagueTrainerOutput
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "LeagueTrainer", uniqueConstraints = [UniqueConstraint(columnNames = ["leagueId", "trainerId"])])
data class LeagueTrainer(

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "leagueId", referencedColumnName = "id", nullable = false)
    val league: League,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "trainerId", referencedColumnName = "id", nullable = false)
    val trainer: Trainer

) : BaseUniqueEntity() {

    val output: LeagueTrainerOutput
        get() {
            return LeagueTrainerOutput(
                leagueName = league.name,
                trainerName = trainer.name
            )
        }

}
