package com.immanuelqrw.ikanobuntai.api.rule

import com.immanuelqrw.ikanobuntai.api.dto.PokemonTeam
import com.immanuelqrw.ikanobuntai.api.entity.TrainerPokemon
import com.immanuelqrw.ikanobuntai.api.entity.Type

object LevelLimitFormat : BattleFormat {

    override val name: String = "LevelLimit"

    override fun verify(pokemonTeam: PokemonTeam, limiter: Any?): Boolean {
        val levelLimit = limiter as Int

        return verify(pokemonTeam, levelLimit)
    }

    private fun verify(pokemonTeam: PokemonTeam, levelLimit: Int): Boolean {
        val (_, trainerPokemon) = pokemonTeam

        return trainerPokemon.all {
            it.level <= levelLimit
        }
    }
}
