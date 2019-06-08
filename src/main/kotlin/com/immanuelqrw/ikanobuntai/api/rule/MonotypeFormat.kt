package com.immanuelqrw.ikanobuntai.api.rule

import com.immanuelqrw.ikanobuntai.api.entity.TrainerPokemon
import com.immanuelqrw.ikanobuntai.api.entity.Type
import com.immanuelqrw.ikanobuntai.api.exception.InvalidPokemonException

/**
 * Checks for specific [Type]
 */
object MonotypeFormat : BattleFormat {

    override val name: String = "Monotype"

    override fun validate(pokemonTeam: Collection<TrainerPokemon>, limiter: Any?) {
        val type = limiter as Type

        return validate(pokemonTeam, type)
    }

    private fun validate(pokemonTeam: Collection<TrainerPokemon>, type: Type) {
        val invalidPokemon = pokemonTeam.filter { trainerPokemon ->
            trainerPokemon.pokemonGeneration.types.contains(type)
        }

        if (invalidPokemon.isNotEmpty()) {
            val invalidMessage = invalidPokemon.joinToString { pokemon ->
                "${pokemon.pokemonGeneration.pokemon.name}:${pokemon.pokemonGeneration.mainType}/${pokemon.pokemonGeneration.subType}"
            }

            throw InvalidPokemonException("Pokémon with type(s) [$invalidMessage] are not allowed in this league.")
        }
    }
}
