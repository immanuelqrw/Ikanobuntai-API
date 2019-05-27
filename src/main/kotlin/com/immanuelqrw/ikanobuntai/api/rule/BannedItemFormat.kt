package com.immanuelqrw.ikanobuntai.api.rule

import com.immanuelqrw.ikanobuntai.api.dto.PokemonTeam
import com.immanuelqrw.ikanobuntai.api.entity.Item

object BannedItemFormat : BattleFormat {

    override val name: String = "BannedItem"

    override fun verify(pokemonTeam: PokemonTeam, limiter: Any?): Boolean {
        val bannedItems = limiter as Collection<Item>

        return verify(pokemonTeam, bannedItems)
    }

    private fun verify(pokemonTeam: PokemonTeam, bannedItems: Collection<Item>): Boolean {
        val (_, trainerPokemon) = pokemonTeam

        return trainerPokemon.all {
            bannedItems.contains(it.item)
        }
    }
}
