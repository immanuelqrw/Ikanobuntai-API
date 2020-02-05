package com.immanuelqrw.ikanobuntai.api.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.immanuelqrw.core.entity.BaseUniqueEntity
import com.immanuelqrw.core.util.DateTimeFormatter
import org.hibernate.annotations.CreationTimestamp
import org.springframework.data.annotation.CreatedDate
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.*

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "Battle", uniqueConstraints = [UniqueConstraint(columnNames = ["defenderId", "challengerId"])])
data class Battle(

    @Enumerated
    @Column(name = "type", nullable = false)
    val type: BattleType,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "defenderId", referencedColumnName = "id", nullable = false)
    val defender: Trainer,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "challengerId", referencedColumnName = "id", nullable = false)
    val challenger: Trainer,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "winnerId", referencedColumnName = "id")
    val winner: Trainer?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "leagueId", referencedColumnName = "id", nullable = false)
    val league: League,

    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @DateTimeFormat(pattern = DateTimeFormatter.DATE_TIME_PATTERN)
    @CreatedDate
    @CreationTimestamp
    @Column(name = "foughtOn", updatable = false, nullable = false)
    val foughtOn: LocalDateTime

) : BaseUniqueEntity()
