package com.immanuelqrw.ikanobuntai.api.dto.output

import com.immanuelqrw.core.util.DateTimeFormatter
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

data class TrainerTitle(

    val trainerString: String,

    val tierName: String,

    val titleName: String,

    @DateTimeFormat(pattern = DateTimeFormatter.DATE_TIME_PATTERN)
    val wonOn: LocalDateTime,

    @DateTimeFormat(pattern = DateTimeFormatter.DATE_TIME_PATTERN)
    val lostOn: LocalDateTime?

)
