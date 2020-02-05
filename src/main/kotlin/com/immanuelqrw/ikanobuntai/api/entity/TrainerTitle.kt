package com.immanuelqrw.ikanobuntai.api.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.immanuelqrw.core.entity.BaseUniqueEntity
import com.immanuelqrw.core.util.DateTimeFormatter
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.*

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "TrainerTitle")
data class TrainerTitle(

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "trainerId", referencedColumnName = "id", nullable = false)
    val trainer: Trainer,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "tierTitleId", referencedColumnName = "id", nullable = false)
    val tierTitle: TierTitle,

    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @DateTimeFormat(pattern = DateTimeFormatter.DATE_TIME_PATTERN)
    @Column(name = "wonOn", updatable = false, nullable = false)
    val wonOn: LocalDateTime,

    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @DateTimeFormat(pattern = DateTimeFormatter.DATE_TIME_PATTERN)
    @Column(name = "lostOn", updatable = false)
    val lostOn: LocalDateTime?

) : BaseUniqueEntity()
