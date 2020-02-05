package com.immanuelqrw.ikanobuntai.api.dto.output

import com.immanuelqrw.core.util.DateTimeFormatter
import com.immanuelqrw.ikanobuntai.api.entity.BattleType
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

data class ScheduledBattle(

    val type: BattleType,

    val defenderName: String,

    val challengerName: String,

    val leagueName: String,

    @DateTimeFormat(pattern = DateTimeFormatter.DATE_TIME_PATTERN)
    val toBeFoughtOn: LocalDateTime,

    val hasConcluded: Boolean

)
