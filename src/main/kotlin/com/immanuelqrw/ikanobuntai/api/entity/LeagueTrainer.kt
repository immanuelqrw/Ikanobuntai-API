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
@Table(name = "`LeagueTrainer`", uniqueConstraints = [UniqueConstraint(columnNames = ["`leagueId`", "`trainerId`"])])
data class LeagueTrainer(

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`leagueId`", referencedColumnName = "`id`")
    val league: League,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`trainerId`", referencedColumnName = "`id`")
    val trainer: Trainer

) : BaseUniqueEntity()
