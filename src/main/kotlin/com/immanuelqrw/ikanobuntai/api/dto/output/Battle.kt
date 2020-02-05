package com.immanuelqrw.ikanobuntai.api.dto.output

import com.immanuelqrw.core.util.DateTimeFormatter
import com.immanuelqrw.ikanobuntai.api.entity.BattleType
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

data class Battle(

    val type: BattleType,

    val defenderName: String,

    val challengerName: String,

    val winnerName: String?,

    val leagueName: String,

    @DateTimeFormat(pattern = DateTimeFormatter.DATE_TIME_PATTERN)
    val foughtOn: LocalDateTime

)
