package com.immanuelqrw.ikanobuntai.api.rule

import com.immanuelqrw.ikanobuntai.api.dto.PokemonTeam
import com.immanuelqrw.ikanobuntai.api.entity.TrainerPokemon
import com.immanuelqrw.ikanobuntai.api.entity.Type

class MonotypeFormat : BattleFormat {

    override val name: String = "Monotype"

    override fun verify(pokemonTeam: PokemonTeam): Boolean {
        val (_, trainerPokemon) = pokemonTeam

        val firstPokemon: TrainerPokemon = trainerPokemon.first()

        val mainType: Type = firstPokemon.pokemonGeneration.mainType
        val subType: Type? = firstPokemon.pokemonGeneration.subType

        var currentTypes = setOf(mainType, subType).filterNotNull().toSet()
        return trainerPokemon.all {
            val pokemonTypes = setOf(it.pokemonGeneration.mainType, it.pokemonGeneration.subType)
            currentTypes = currentTypes.intersect(pokemonTypes) as Set<Type>

            currentTypes.isNotEmpty()
        }
    }
}
