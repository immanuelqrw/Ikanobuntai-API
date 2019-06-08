package com.immanuelqrw.ikanobuntai.api.rule

import com.immanuelqrw.ikanobuntai.api.entity.TrainerPokemon
import com.immanuelqrw.ikanobuntai.api.entity.Type

/**
 * Checks that team shares at least one [Type]
 */
object SharedMonotypeFormat : BattleFormat {

    override val name: String = "SharedMonotype"

    override fun verify(pokemonTeam: Collection<TrainerPokemon>, limiter: Any?): Boolean {
        return verify(pokemonTeam)
    }

    private fun verify(pokemonTeam: Collection<TrainerPokemon>): Boolean {
        val firstPokemon: TrainerPokemon = pokemonTeam.first()

        var currentTypes = firstPokemon.pokemonGeneration.types
        return pokemonTeam.all { trainerPokemon ->
            currentTypes = currentTypes.intersect(trainerPokemon.pokemonGeneration.types)

            currentTypes.isNotEmpty()
        }
    }
}
