package com.immanuelqrw.ikanobuntai.api.rule

import com.immanuelqrw.ikanobuntai.api.entity.PokemonGeneration
import com.immanuelqrw.ikanobuntai.api.entity.TrainerPokemon

object BannedPokemonFormat : BattleFormat {

    override val name: String = "BannedPokemon"

    override fun verify(pokemonTeam: Collection<TrainerPokemon>, limiter: Any?): Boolean {
        val bannedPokemon = limiter as Collection<PokemonGeneration>

        return verify(pokemonTeam, bannedPokemon)
    }

    private fun verify(pokemonTeam: Collection<TrainerPokemon>, bannedPokemon: Collection<PokemonGeneration>): Boolean {
        return pokemonTeam.all { trainerPokemon ->
            bannedPokemon.contains(trainerPokemon.pokemonGeneration)
        }
    }
}
