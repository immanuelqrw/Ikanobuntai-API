package com.immanuelqrw.ikanobuntai.api.rule

import com.immanuelqrw.ikanobuntai.api.entity.TrainerPokemon
import com.immanuelqrw.ikanobuntai.api.entity.Type

/**
 * Checks for specific [Type]
 */
object MonotypeFormat : BattleFormat {

    override val name: String = "Monotype"

    override fun verify(pokemonTeam: Collection<TrainerPokemon>, limiter: Any?): Boolean {
        val type = limiter as Type

        return verify(pokemonTeam, type)
    }

    private fun verify(pokemonTeam: Collection<TrainerPokemon>, type: Type): Boolean {
        return pokemonTeam.all { trainerPokemon ->
            trainerPokemon.pokemonGeneration.types.contains(type)
        }
    }
}
