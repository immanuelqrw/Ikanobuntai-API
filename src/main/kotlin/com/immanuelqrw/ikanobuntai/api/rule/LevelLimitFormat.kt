package com.immanuelqrw.ikanobuntai.api.rule

import com.immanuelqrw.ikanobuntai.api.entity.TrainerPokemon
import com.immanuelqrw.ikanobuntai.api.exception.InvalidPokemonException

object LevelLimitFormat : BattleFormat {

    override val name: String = "LevelLimit"

    override fun validate(pokemonTeam: Collection<TrainerPokemon>, limiter: Any?) {
        val levelLimit = limiter as Int

        return validate(pokemonTeam, levelLimit)
    }

    private fun validate(pokemonTeam: Collection<TrainerPokemon>, levelLimit: Int) {
        val invalidPokemon =  pokemonTeam.filter { trainerPokemon ->
            trainerPokemon.level <= levelLimit
        }

        if (invalidPokemon.isNotEmpty()) {
            val invalidMessage = invalidPokemon.joinToString { pokemon ->
                "${pokemon.pokemonGeneration.pokemon.name}:${pokemon.level}"
            }

            throw InvalidPokemonException("Pokémon with level(s) [$invalidMessage] are not allowed in this league.")
        }
    }
}
