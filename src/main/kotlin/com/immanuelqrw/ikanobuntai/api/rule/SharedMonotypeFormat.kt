package com.immanuelqrw.ikanobuntai.api.rule

import com.immanuelqrw.ikanobuntai.api.dto.BattleTeam
import com.immanuelqrw.ikanobuntai.api.entity.TrainerPokemon
import com.immanuelqrw.ikanobuntai.api.entity.Type

/**
 * Checks that team shares at least one [Type]
 */
object SharedMonotypeFormat : BattleFormat {

    override val name: String = "SharedMonotype"

    override fun verify(pokemonTeam: BattleTeam, limiter: Any?): Boolean {
        return verify(pokemonTeam)
    }

    private fun verify(pokemonTeam: BattleTeam): Boolean {
        val (_, trainerPokemon) = pokemonTeam

        val firstPokemon: TrainerPokemon = trainerPokemon.first()

        var currentTypes = firstPokemon.pokemonGeneration.types
        return trainerPokemon.all {
            currentTypes = currentTypes.intersect(it.pokemonGeneration.types)

            currentTypes.isNotEmpty()
        }
    }
}
