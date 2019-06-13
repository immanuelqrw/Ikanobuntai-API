package com.immanuelqrw.ikanobuntai.api.service.rule

import com.immanuelqrw.ikanobuntai.api.entity.Format
import com.immanuelqrw.ikanobuntai.api.entity.Format.*
import com.immanuelqrw.ikanobuntai.api.entity.Item
import com.immanuelqrw.ikanobuntai.api.entity.PokemonGeneration
import com.immanuelqrw.ikanobuntai.api.entity.TrainerPokemon
import com.immanuelqrw.ikanobuntai.api.entity.Type
import com.immanuelqrw.ikanobuntai.api.exception.InvalidPokemonException
import org.springframework.stereotype.Service

@Service
class FormatRuleService : Rule {

    fun validate(format: Format, pokemonTeam: Collection<TrainerPokemon>, limiter: Any?) {
        when(format) {
            BANNED_ITEM -> {
                val bannedItems = limiter as Collection<Item>

                validateItems(pokemonTeam, bannedItems)
            }
            BANNED_POKEMON -> {
                val bannedPokemon = limiter as Collection<PokemonGeneration>

                validatePokemon(pokemonTeam, bannedPokemon)
            }
            LEVEL_LIMIT -> {
                val levelLimit = limiter as Int

                validateLevel(pokemonTeam, levelLimit)
            }
            MONOTYPE -> {
                val type = limiter as Type

                validateType(pokemonTeam, type)
            }
            SHARED_MONOTYPE -> {
                validateSharedType(pokemonTeam)
            }
            LEVEL_RANGE -> {
                val range: List<String> = (limiter as String).split(":")
                val minLevel = range.first().toInt()
                val maxLevel = range.last().toInt()
                validateLevelRange(pokemonTeam, minLevel, maxLevel)
            }
            NO_MEGA -> {
                validateNoMega(pokemonTeam)
            }
            NO_LEGENDARY -> {
                validateNoLegendary(pokemonTeam)
            }
            NO_MYTHICAL -> {
                validateNoMythical(pokemonTeam)
            }
        }
    }

    private fun validateItems(pokemonTeam: Collection<TrainerPokemon>, bannedItems: Collection<Item>) {
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

    private fun validatePokemon(pokemonTeam: Collection<TrainerPokemon>, bannedPokemon: Collection<PokemonGeneration>) {
        val invalidPokemon = pokemonTeam.filter { trainerPokemon ->
            bannedPokemon.contains(trainerPokemon.pokemonGeneration)
        }

        if (invalidPokemon.isNotEmpty()) {
            val invalidMessage = invalidPokemon.joinToString { pokemon ->
                pokemon.pokemonGeneration.pokemon.name
            }

            throw InvalidPokemonException("Pokémon [$invalidMessage] are not allowed in this league.")
        }
    }

    private fun validateLevel(pokemonTeam: Collection<TrainerPokemon>, levelLimit: Int) {
        val invalidPokemon =  pokemonTeam.filter { trainerPokemon ->
            trainerPokemon.level <= levelLimit
        }

        if (invalidPokemon.isNotEmpty()) {
            val invalidMessage = invalidPokemon.joinToString { pokemon ->
                "${pokemon.pokemonGeneration.pokemon.name}:${pokemon.level}"
            }

            throw InvalidPokemonException("Pokémon with level(s) [$invalidMessage] are not allowed in this league.")
        }
    }

    private fun validateType(pokemonTeam: Collection<TrainerPokemon>, type: Type) {
        val invalidPokemon = pokemonTeam.filter { trainerPokemon ->
            trainerPokemon.pokemonGeneration.types.contains(type)
        }

        if (invalidPokemon.isNotEmpty()) {
            val invalidMessage = invalidPokemon.joinToString { pokemon ->
                "${pokemon.pokemonGeneration.pokemon.name}:${pokemon.pokemonGeneration.mainType}/${pokemon.pokemonGeneration.subType}"
            }

            throw InvalidPokemonException("Pokémon with type(s) [$invalidMessage] are not allowed in this league.")
        }
    }

    private fun validateSharedType(pokemonTeam: Collection<TrainerPokemon>) {
        val firstPokemon: TrainerPokemon = pokemonTeam.first()

        var currentTypes = firstPokemon.pokemonGeneration.types
        val invalidPokemon = pokemonTeam.filter { trainerPokemon ->
            currentTypes = currentTypes.intersect(trainerPokemon.pokemonGeneration.types)

            currentTypes.isNotEmpty()
        }

        if (invalidPokemon.isNotEmpty()) {
            val invalidMessage = invalidPokemon.joinToString { pokemon ->
                "${pokemon.pokemonGeneration.pokemon.name}:${pokemon.pokemonGeneration.mainType}/${pokemon.pokemonGeneration.subType}"
            }

            throw InvalidPokemonException("Pokémon with type(s) [$invalidMessage] are not allowed in this league.")
        }
    }

    private fun validateLevelRange(pokemonTeam: Collection<TrainerPokemon>, minLevel: Int, maxLevel: Int) {
        val invalidPokemon = pokemonTeam.filter { trainerPokemon ->
            trainerPokemon.level !in minLevel..maxLevel
        }

        if (invalidPokemon.isNotEmpty()) {
            val invalidMessage = invalidPokemon.joinToString { pokemon ->
                "${pokemon.pokemonGeneration.pokemon.name}:${pokemon.level}"
            }

            throw InvalidPokemonException("Pokémon with levels [$invalidMessage] are not allowed in this league.")
        }
    }

    private fun validateNoMega(pokemonTeam: Collection<TrainerPokemon>) {
        val invalidPokemon = pokemonTeam.filter { trainerPokemon ->
            trainerPokemon.pokemonGeneration.pokemon.isMega
        }

        if (invalidPokemon.isNotEmpty()) {
            val invalidMessage = invalidPokemon.joinToString { pokemon ->
                pokemon.pokemonGeneration.pokemon.form!!
            }

            throw InvalidPokemonException("Mega Pokémon [$invalidMessage] are not allowed in this league.")
        }
    }

    private fun validateNoLegendary(pokemonTeam: Collection<TrainerPokemon>) {
        val invalidPokemon = pokemonTeam.filter { trainerPokemon ->
            trainerPokemon.pokemonGeneration.pokemon.isLegendary
        }

        if (invalidPokemon.isNotEmpty()) {
            val invalidMessage = invalidPokemon.joinToString { pokemon ->
                pokemon.pokemonGeneration.pokemon.name
            }

            throw InvalidPokemonException("Legendary Pokémon [$invalidMessage] are not allowed in this league.")
        }
    }

    private fun validateNoMythical(pokemonTeam: Collection<TrainerPokemon>) {
        val invalidPokemon = pokemonTeam.filter { trainerPokemon ->
            trainerPokemon.pokemonGeneration.pokemon.isMythical
        }

        if (invalidPokemon.isNotEmpty()) {
            val invalidMessage = invalidPokemon.joinToString { pokemon ->
                pokemon.pokemonGeneration.pokemon.name
            }

            throw InvalidPokemonException("Mythical Pokémon [$invalidMessage] are not allowed in this league.")
        }
    }
}
