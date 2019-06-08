package com.immanuelqrw.ikanobuntai.api.rule

import com.immanuelqrw.ikanobuntai.api.entity.TrainerPokemon
import com.immanuelqrw.ikanobuntai.api.entity.Type
import com.immanuelqrw.ikanobuntai.api.exception.InvalidPokemonException

/**
 * Checks that team shares at least one [Type]
 */
object SharedMonotypeFormat : BattleFormat {

    override val name: String = "SharedMonotype"

    override fun validate(pokemonTeam: Collection<TrainerPokemon>, limiter: Any?) {
        return validate(pokemonTeam)
    }

    private fun validate(pokemonTeam: Collection<TrainerPokemon>) {
        val firstPokemon: TrainerPokemon = pokemonTeam.first()

        var currentTypes = firstPokemon.pokemonGeneration.types
        val invalidPokemon = pokemonTeam.filter { trainerPokemon ->
            currentTypes = currentTypes.intersect(trainerPokemon.pokemonGeneration.types)

            currentTypes.isNotEmpty()
        }

        if (invalidPokemon.isNotEmpty()) {
            val invalidMessage = invalidPokemon.joinToString { pokemon ->
                "${pokemon.pokemonGeneration.pokemon.name}:${pokemon.pokemonGeneration.mainType}/${pokemon.pokemonGeneration.subType}"
            }

            throw InvalidPokemonException("Pokémon with type(s) [$invalidMessage] are not allowed in this league.")
        }
    }
}
