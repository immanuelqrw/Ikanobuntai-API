package com.immanuelqrw.ikanobuntai.api.dto.input

import com.immanuelqrw.core.util.DateTimeFormatter
import com.immanuelqrw.ikanobuntai.api.entity.LeagueType
import com.immanuelqrw.ikanobuntai.api.entity.Stage
import com.immanuelqrw.ikanobuntai.api.entity.Tier
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

data class League(

    val name: String,

    val type: LeagueType,

    val stage: Stage,

    val tier: Tier,

    val stdDev: Int,

    val kFactor: Int,

    @DateTimeFormat(pattern = DateTimeFormatter.DATE_TIME_PATTERN)
    val startedOn: LocalDateTime = LocalDateTime.now(),

    val prizeMin: Int

)
