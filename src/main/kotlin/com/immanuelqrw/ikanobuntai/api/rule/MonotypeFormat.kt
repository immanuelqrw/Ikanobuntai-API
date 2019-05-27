package com.immanuelqrw.ikanobuntai.api.rule

import com.immanuelqrw.ikanobuntai.api.dto.PokemonTeam
import com.immanuelqrw.ikanobuntai.api.entity.Type

/**
 * Checks for specific [Type]
 */
object MonotypeFormat : BattleFormat {

    override val name: String = "Monotype"

    override fun verify(pokemonTeam: PokemonTeam, limiter: Any?): Boolean {
        val type = limiter as Type

        return verify(pokemonTeam, type)
    }

    private fun verify(pokemonTeam: PokemonTeam, type: Type): Boolean {
        val (_, trainerPokemon) = pokemonTeam

        return trainerPokemon.all {
            it.pokemonGeneration.types.contains(type)
        }
    }
}
