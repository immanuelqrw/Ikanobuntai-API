package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity
import java.time.LocalDateTime

data class Elo(
    val divider: Int,
    val k: Int
) : BaseUniqueEntity()

data class Rank(
    val name: String
) : BaseUniqueEntity()

data class Tier(
    val name: String
) : BaseUniqueEntity()

data class Stage(
    val name: String
) : BaseUniqueEntity()

data class Title(
    val name: String,
    val tier: Tier
) : BaseUniqueEntity()

data class Configuration(
    val name: String,
    val value: String,
    val type: String,
    val tier: Tier
) : BaseUniqueEntity()

data class League(
    val name: String,
    val stage: Stage,
    val tier: Tier,
    val elo: Elo
) : BaseUniqueEntity()

data class TrainerUser(
    val name: String,
    val email: String,
    val passwordHash: String,
    val passwordSalt: String,
    val oAuthId: String
) : BaseUniqueEntity()

data class Trainer(
    val name: String,
    val trainerUser: TrainerUser,
    val tier: Tier,
    val rank: Rank,
    val rating: Int
) : BaseUniqueEntity()

data class TrainerTitle(
    val trainer: Trainer,
    val title: Title,
    val wonOn: LocalDateTime?,
    val lostOn: LocalDateTime?
) : BaseUniqueEntity()

data class BattleRating(
    val defenderTitle: Title,
    val challengerTitle: Title,
    val league: League,
    val value: Int
) : BaseUniqueEntity()

enum class BattleResult {
    WIN,
    DRAW,
    LOSS
}
data class Battle(
    val defender: Trainer,
    val challenger: Trainer,
    val winner: Trainer,
    val rank: Rank,
    val value: Int,
    val foughtOn: LocalDateTime
) : BaseUniqueEntity()

data class Ability(
    val name: String,
    val description: String
) : BaseUniqueEntity()

enum class Stat {
    HP,
    ATTACK,
    DEFENSE,
    SPECIAL_ATTACK,
    SPECIAL_DEFENSE,
    SPEED
}
// ? Hardcoded
data class Nature(
    val name: String,
    val dropStat: Stat,
    val riseStat: Stat
) : BaseUniqueEntity()

// ? Limit values in code
data class IndividualValue(
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val specialAttack: Int,
    val specialDefense: Int,
    val speed: Int
) : BaseUniqueEntity()

// ? Keep as table in case of additions
enum class Type {
    GRASS,
    FIRE,
    WATER,
    ELECTRIC,
    NORMAL,
    GROUND,
    ROCK,
    FIGHTING,
    FLYING,
    ICE,
    POISON,
    BUG,
    PSYCHIC,
    GHOST,
    DRAGON,
    DARK,
    STEEL,
    FAIRY
}
data class Generation(
    val number: Int,
    val region: String
) : BaseUniqueEntity()

data class Pokemon(
    val number: String,
    val name: String,
    val form: String?,
    val isLegendary: Boolean,
    val isMythical: Boolean,
    val isMega: Boolean,
    val prevolvedPokemon: Pokemon?
) : BaseUniqueEntity()

data class PokemonGeneration(
    val pokemon: Pokemon,
    val generation: Generation,
    val mainType: Type,
    val subType: Type?,
    val stage: Int,
    val baseStatTotal: Int,
    val hpBaseStat: Int,
    val attackBaseStat: Int,
    val defenseBaseStat: Int,
    val specialAttackBaseStat: Int,
    val specialDefenseBaseStat: Int,
    val speedBaseStat: Int,
    val ability: Ability,
    val alternateAbility: Ability?,
    val hiddenAbility: Ability?
) : BaseUniqueEntity()

data class PokemonTier(
    val pokemon: Pokemon,
    val generation: Generation,
    val tier: Tier
) : BaseUniqueEntity()

data class PokemonTrainer(
    val pokemon: Pokemon,
    val trainer: Trainer,
    val nature: Nature,
    val ability: Ability,
    val individualValue: IndividualValue
) : BaseUniqueEntity()
