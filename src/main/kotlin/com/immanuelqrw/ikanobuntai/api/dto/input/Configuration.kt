package com.immanuelqrw.ikanobuntai.api.dto.input

import com.immanuelqrw.ikanobuntai.api.entity.ConfigurationType
import com.immanuelqrw.ikanobuntai.api.entity.Format

data class Configuration(

    val leagueName: String,

    val format: Format,

    val value: String,

    val type: ConfigurationType

)
