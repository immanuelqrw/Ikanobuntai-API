package com.immanuelqrw.ikanobuntai.api.rule

import com.immanuelqrw.ikanobuntai.api.dto.BattleTeam
import com.immanuelqrw.ikanobuntai.api.entity.Item

object BannedItemFormat : BattleFormat {

    override val name: String = "BannedItem"

    override fun verify(pokemonTeam: BattleTeam, limiter: Any?): Boolean {
        val bannedItems = limiter as Collection<Item>

        return verify(pokemonTeam, bannedItems)
    }

    private fun verify(pokemonTeam: BattleTeam, bannedItems: Collection<Item>): Boolean {
        val (_, trainerPokemon) = pokemonTeam

        return trainerPokemon.all {
            bannedItems.contains(it.item)
        }
    }
}
