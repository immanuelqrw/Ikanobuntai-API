package com.immanuelqrw.ikanobuntai.api.rule

import com.immanuelqrw.ikanobuntai.api.entity.TrainerPokemon

object LevelLimitFormat : BattleFormat {

    override val name: String = "LevelLimit"

    override fun verify(pokemonTeam: Collection<TrainerPokemon>, limiter: Any?): Boolean {
        val levelLimit = limiter as Int

        return verify(pokemonTeam, levelLimit)
    }

    private fun verify(pokemonTeam: Collection<TrainerPokemon>, levelLimit: Int): Boolean {
        return pokemonTeam.all {
            it.level <= levelLimit
        }
    }
}
