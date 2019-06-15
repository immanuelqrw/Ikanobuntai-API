package com.immanuelqrw.ikanobuntai.api.dto

import java.util.UUID

data class TeamVerification(
    val defenderTeam: String,
    val challengerTeam: String,
    val leagueId: UUID
)
