package com.immanuelqrw.ikanobuntai.api.rule

import com.immanuelqrw.ikanobuntai.api.dto.BattleTeam

/**
 * Interface to verify teams for battle format
 */
interface BattleFormat {

    /**
     * Name of BattleFormat
     */
    val name: String

    /**
     * Checks to see if teams are valid for format
     */
    fun verify(pokemonTeam: BattleTeam, limiter: Any? = null): Boolean
}
