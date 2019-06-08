package com.immanuelqrw.ikanobuntai.api.rule

import com.immanuelqrw.ikanobuntai.api.dto.BattleTeam

object LevelLimitFormat : BattleFormat {

    override val name: String = "LevelLimit"

    override fun verify(pokemonTeam: BattleTeam, limiter: Any?): Boolean {
        val levelLimit = limiter as Int

        return verify(pokemonTeam, levelLimit)
    }

    private fun verify(pokemonTeam: BattleTeam, levelLimit: Int): Boolean {
        val (_, trainerPokemon) = pokemonTeam

        return trainerPokemon.all {
            it.level <= levelLimit
        }
    }
}
