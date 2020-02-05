package com.immanuelqrw.ikanobuntai.api.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.immanuelqrw.core.entity.BaseUniqueEntity
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "TrainerPrize")
data class TrainerPrize(

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "trainerId", referencedColumnName = "id", nullable = false)
    val trainer: Trainer,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "prizeId", referencedColumnName = "id", nullable = false)
    val prize: Prize,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "leagueId", referencedColumnName = "id", nullable = false)
    val league: League

) : BaseUniqueEntity()
