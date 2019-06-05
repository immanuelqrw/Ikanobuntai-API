package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "`TrainerPrize`")
data class TrainerPrize(

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`trainerId`", referencedColumnName = "`id`")
    val trainer: Trainer,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`prizeId`", referencedColumnName = "`id`")
    val prize: Prize,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`leagueId`", referencedColumnName = "`id`")
    val league: League

) : BaseUniqueEntity()
