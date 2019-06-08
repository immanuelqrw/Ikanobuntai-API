package com.immanuelqrw.ikanobuntai.api.rule

import com.immanuelqrw.ikanobuntai.api.entity.Item
import com.immanuelqrw.ikanobuntai.api.entity.TrainerPokemon
import com.immanuelqrw.ikanobuntai.api.exception.InvalidPokemonException

object BannedItemFormat : BattleFormat {

    override val name: String = "BannedItem"

    override fun validate(pokemonTeam: Collection<TrainerPokemon>, limiter: Any?) {
        val bannedItems = limiter as Collection<Item>

        return validate(pokemonTeam, bannedItems)
    }

    private fun validate(pokemonTeam: Collection<TrainerPokemon>, bannedItems: Collection<Item>) {
        val invalidPokemon = pokemonTeam.filter { trainerPokemon ->
            bannedItems.contains(trainerPokemon.item)
        }

        if (invalidPokemon.isNotEmpty()) {
            val invalidMessage = invalidPokemon.joinToString { pokemon ->
                "${pokemon.pokemonGeneration.pokemon.name}:${pokemon.item}"
            }

            throw InvalidPokemonException("Pokémon with item(s) [$invalidMessage] are not allowed in this league.")
        }
    }
}
