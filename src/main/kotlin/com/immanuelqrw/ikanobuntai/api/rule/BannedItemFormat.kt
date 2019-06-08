package com.immanuelqrw.ikanobuntai.api.rule

import com.immanuelqrw.ikanobuntai.api.entity.Item
import com.immanuelqrw.ikanobuntai.api.entity.TrainerPokemon

object BannedItemFormat : BattleFormat {

    override val name: String = "BannedItem"

    override fun verify(pokemonTeam: Collection<TrainerPokemon>, limiter: Any?): Boolean {
        val bannedItems = limiter as Collection<Item>

        return verify(pokemonTeam, bannedItems)
    }

    private fun verify(pokemonTeam: Collection<TrainerPokemon>, bannedItems: Collection<Item>): Boolean {
        return pokemonTeam.all { trainerPokemon ->
            bannedItems.contains(trainerPokemon.item)
        }
    }
}
