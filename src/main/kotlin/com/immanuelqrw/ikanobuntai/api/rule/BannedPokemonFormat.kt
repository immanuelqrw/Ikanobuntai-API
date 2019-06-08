package com.immanuelqrw.ikanobuntai.api.rule

import com.immanuelqrw.ikanobuntai.api.entity.PokemonGeneration
import com.immanuelqrw.ikanobuntai.api.entity.TrainerPokemon
import com.immanuelqrw.ikanobuntai.api.exception.InvalidPokemonException

object BannedPokemonFormat : BattleFormat {

    override val name: String = "BannedPokemon"

    override fun validate(pokemonTeam: Collection<TrainerPokemon>, limiter: Any?) {
        val bannedPokemon = limiter as Collection<PokemonGeneration>

        return validate(pokemonTeam, bannedPokemon)
    }

    private fun validate(pokemonTeam: Collection<TrainerPokemon>, bannedPokemon: Collection<PokemonGeneration>) {
        val invalidPokemon = pokemonTeam.filter { trainerPokemon ->
            bannedPokemon.contains(trainerPokemon.pokemonGeneration)
        }

        if (invalidPokemon.isNotEmpty()) {
            val invalidMessage = invalidPokemon.joinToString { pokemon ->
                pokemon.pokemonGeneration.pokemon.name
            }

            throw InvalidPokemonException("Pokémon [$invalidMessage] are not allowed in this league.")
        }
    }
}
