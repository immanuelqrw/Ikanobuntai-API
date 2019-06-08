package com.immanuelqrw.ikanobuntai.api.rule

import com.immanuelqrw.ikanobuntai.api.dto.BattleTeam
import com.immanuelqrw.ikanobuntai.api.entity.PokemonGeneration

object BannedPokemonFormat : BattleFormat {

    override val name: String = "BannedPokemon"

    override fun verify(pokemonTeam: BattleTeam, limiter: Any?): Boolean {
        val bannedPokemon = limiter as Collection<PokemonGeneration>

        return verify(pokemonTeam, bannedPokemon)
    }

    private fun verify(pokemonTeam: BattleTeam, bannedPokemon: Collection<PokemonGeneration>): Boolean {
        val (_, trainerPokemon) = pokemonTeam

        return trainerPokemon.all {
            bannedPokemon.contains(it.pokemonGeneration)
        }
    }
}
