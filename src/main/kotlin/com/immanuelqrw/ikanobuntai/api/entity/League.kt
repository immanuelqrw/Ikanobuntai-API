package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "`League`")
data class League(

    @Column(unique = true)
    val name: String,

    @Enumerated
    val type: LeagueType,

    @Enumerated
    val stage: Stage,

    @Enumerated
    val tier: Tier,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`eloId`", referencedColumnName = "`id`")
    val elo: Elo

) : BaseUniqueEntity()
