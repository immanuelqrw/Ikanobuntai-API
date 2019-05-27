package com.immanuelqrw.ikanobuntai.api.dto

import com.immanuelqrw.ikanobuntai.api.entity.Format
import com.immanuelqrw.ikanobuntai.api.entity.League

data class PokemonLeague(
    val league: League,
    val leagueFormats: Collection<Format>
)
