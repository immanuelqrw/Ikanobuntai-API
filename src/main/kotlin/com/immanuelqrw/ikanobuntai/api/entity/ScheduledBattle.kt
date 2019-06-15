package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "`ScheduledBattle`")
data class ScheduledBattle(

    @Enumerated
    val type: BattleType,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`defenderId`", referencedColumnName = "`id`")
    val defender: Trainer,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`challengerId`", referencedColumnName = "`id`")
    val challenger: Trainer,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`leagueId`", referencedColumnName = "`id`")
    val league: League,

    @DateTimeFormat
    val toBeFoughtOn: LocalDateTime,

    val hasConcluded: Boolean = false

) : BaseUniqueEntity()
