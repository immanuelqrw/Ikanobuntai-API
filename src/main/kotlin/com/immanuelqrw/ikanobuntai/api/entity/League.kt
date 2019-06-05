package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Enumerated
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

    val stdDev: Int,

    val kFactor: Int,

    @DateTimeFormat
    val startedOn: LocalDateTime,

    val prizeMin: Int

) : BaseUniqueEntity()
