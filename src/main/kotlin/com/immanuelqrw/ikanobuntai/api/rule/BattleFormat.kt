package com.immanuelqrw.ikanobuntai.api.rule

import com.immanuelqrw.ikanobuntai.api.entity.TrainerPokemon

/**
 * Interface to validate teams for battle format
 */
interface BattleFormat {

    /**
     * Name of BattleFormat
     */
    val name: String

    /**
     * Checks to see if teams are valid for format
     */
    fun validate(pokemonTeam: Collection<TrainerPokemon>, limiter: Any? = null)
}
