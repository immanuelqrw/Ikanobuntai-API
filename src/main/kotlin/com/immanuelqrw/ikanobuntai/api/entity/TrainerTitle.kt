package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity
import java.time.LocalDateTime

data class TrainerTitle(
    val trainer: Trainer,
    val title: Title,
    val wonOn: LocalDateTime?,
    val lostOn: LocalDateTime?
) : BaseUniqueEntity()
