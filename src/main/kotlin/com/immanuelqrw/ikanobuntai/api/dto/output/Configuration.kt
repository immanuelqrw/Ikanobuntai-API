package com.immanuelqrw.ikanobuntai.api.dto.output

import com.immanuelqrw.ikanobuntai.api.entity.ConfigurationType

data class Configuration(

    val leagueFormat: LeagueFormat,

    val value: String,

    val type: ConfigurationType

)
