package com.immanuelqrw.ikanobuntai.api.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.immanuelqrw.core.entity.BaseUniqueEntity
import com.immanuelqrw.ikanobuntai.api.dto.output.PrizeGrunt as PrizeGruntOutput
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "PrizeGrunt", uniqueConstraints = [UniqueConstraint(columnNames = ["trainerId", "leagueId"])])
data class PrizeGrunt(

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "trainerId", referencedColumnName = "id", nullable = false)
    val trainer: Trainer,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "tierTitleId", referencedColumnName = "id", nullable = false)
    val tierTitle: TierTitle,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "leagueId", referencedColumnName = "id", nullable = false)
    val league: League

) : BaseUniqueEntity() {

    val output: PrizeGruntOutput
        get() {
            return PrizeGruntOutput(
                trainerName = trainer.name,
                tierName = tierTitle.tier.name,
                titleName = tierTitle.title.name,
                leagueName = league.name
            )
        }

}
