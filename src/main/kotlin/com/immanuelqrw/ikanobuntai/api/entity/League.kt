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
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Enumerated
import javax.persistence.Table

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "League")
data class League(

    @Column(name = "name", unique = true, nullable = false)
    val name: String,

    @Enumerated
    @Column(name = "type", nullable = false)
    val type: LeagueType,

    @Enumerated
    @Column(name = "stage", nullable = false)
    val stage: Stage,

    @Enumerated
    @Column(name = "tier", nullable = false)
    val tier: Tier,

    @Column(name = "stdDev", nullable = false)
    val stdDev: Int,

    @Column(name = "kFactor", nullable = false)
    val kFactor: Int,

    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @DateTimeFormat(pattern = DateTimeFormatter.DATE_TIME_PATTERN)
    @CreatedDate
    @CreationTimestamp
    @Column(name = "startedOn", updatable = false, nullable = false)
    val startedOn: LocalDateTime,

    @Column(name = "prizeMin", nullable = false)
    val prizeMin: Int

) : BaseUniqueEntity()
