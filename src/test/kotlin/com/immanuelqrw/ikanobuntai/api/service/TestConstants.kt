package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.entity.*
import java.time.LocalDateTime

object TestConstants {

    const val VALID_ABILITY_NAME: String = "Protean"
    const val INVALID_ABILITY_NAME: String = "Fast Start"

    const val VALID_ABILITY_EFFECT: String = "Changes the Pokémon’s type to the type of the move it’s about to use."
    const val INVALID_ABILITY_EFFECT: String = "Amplifies the Pokémon's speed to double the base"

    val VALID_BATTLE_TYPE: BattleType = BattleType.GYM
    val INVALID_BATTLE_TYPE: BattleType = BattleType.WILD

    const val VALID_TRAINER_NAME: String = "Red"
    const val INVALID_TRAINER_NAME: String = "Blue"

    const val VALID_DEFENDER_NAME: String = "Steven"
    const val INVALID_DEFENDER_NAME: String = "Wallace"

    const val VALID_CHALLENGER_NAME: String = "Cynthia"
    const val INVALID_CHALLENGER_NAME: String = "Diantha"

    const val VALID_WINNER_NAME: String = "Ghetsis"
    const val INVALID_WINNER_NAME: String = "N"

    const val VALID_LEAGUE_NAME: String = "Indigo"
    const val INVALID_LEAGUE_NAME: String = "Lily of the Valley"

    val VALID_FOUGHT_ON: LocalDateTime = LocalDateTime.now()
    val INVALID_FOUGHT_ON: LocalDateTime = LocalDateTime.MAX

    val VALID_FORMAT: Format = Format.MONOTYPE
    val INVALID_FORMAT: Format = Format.NO_LEGENDARY

    const val VALID_VALUE: String = "1"
    const val INVALID_VALUE: String = "Pokemon(name=Bulbasaur)"

    val VALID_CONFIGURATION_TYPE: ConfigurationType = ConfigurationType.STRING
    val INVALID_CONFIGURATION_TYPE: ConfigurationType = ConfigurationType.COLLECTION

    const val VALID_TOTAL_BASE_STAT: Int = 600
    const val VALID_HP_BASE_STAT: Int = 100
    const val VALID_ATTACK_BASE_STAT: Int = 100
    const val VALID_DEFENSE_BASE_STAT: Int = 100
    const val VALID_SPECIAL_ATTACK_BASE_STAT: Int = 100
    const val VALID_SPECIAL_DEFENSE_BASE_STAT: Int = 100
    const val VALID_SPEED_BASE_STAT: Int = 100

    const val INVALID_TOTAL_BASE_STAT: Int = -1
    const val INVALID_HP_BASE_STAT: Int = -1
    const val INVALID_ATTACK_BASE_STAT: Int = -1
    const val INVALID_DEFENSE_BASE_STAT: Int = -1
    const val INVALID_SPECIAL_ATTACK_BASE_STAT: Int = -1
    const val INVALID_SPECIAL_DEFENSE_BASE_STAT: Int = -1
    const val INVALID_SPEED_BASE_STAT: Int = -1

    const val VALID_HP_EFFORT_VALUE: Int = 252
    const val VALID_ATTACK_EFFORT_VALUE: Int = 252
    const val VALID_DEFENSE_EFFORT_VALUE: Int = 252
    const val VALID_SPECIAL_ATTACK_EFFORT_VALUE: Int = 252
    const val VALID_SPECIAL_DEFENSE_EFFORT_VALUE: Int = 252
    const val VALID_SPEED_EFFORT_VALUE: Int = 252

    const val INVALID_HP_EFFORT_VALUE: Int = -1
    const val INVALID_ATTACK_EFFORT_VALUE: Int = -1
    const val INVALID_DEFENSE_EFFORT_VALUE: Int = -1
    const val INVALID_SPECIAL_ATTACK_EFFORT_VALUE: Int = -1
    const val INVALID_SPECIAL_DEFENSE_EFFORT_VALUE: Int = -1
    const val INVALID_SPEED_EFFORT_VALUE: Int = -1

    const val VALID_HP_INDIVIDUAL_VALUE: Int = 31
    const val VALID_ATTACK_INDIVIDUAL_VALUE: Int = 31
    const val VALID_DEFENSE_INDIVIDUAL_VALUE: Int = 31
    const val VALID_SPECIAL_ATTACK_INDIVIDUAL_VALUE: Int = 31
    const val VALID_SPECIAL_DEFENSE_INDIVIDUAL_VALUE: Int = 31
    const val VALID_SPEED_INDIVIDUAL_VALUE: Int = 31

    const val INVALID_HP_INDIVIDUAL_VALUE: Int = -1
    const val INVALID_ATTACK_INDIVIDUAL_VALUE: Int = -1
    const val INVALID_DEFENSE_INDIVIDUAL_VALUE: Int = -1
    const val INVALID_SPECIAL_ATTACK_INDIVIDUAL_VALUE: Int = -1
    const val INVALID_SPECIAL_DEFENSE_INDIVIDUAL_VALUE: Int = -1
    const val INVALID_SPEED_INDIVIDUAL_VALUE: Int = -1

    const val VALID_ITEM_NAME: String = "Leftovers"
    const val INVALID_ITEM_NAME: String = "Smoke Ball"

    const val VALID_ITEM_EFFECT: String = "Restores 1/16 of the holder's maximum HP (rounded down, but not less than 1) at the end of each turn."
    const val INVALID_ITEM_EFFECT: String = "If held by the player's Pokémon, fleeing from a wild Pokémon is guaranteed to succeed"

    val VALID_LEAGUE_TYPE: LeagueType = LeagueType.LEAGUE
    val INVALID_LEAGUE_TYPE: LeagueType = LeagueType.WILD

    val VALID_STAGE: Stage = Stage.QUALIFIER
    val INVALID_STAGE: Stage = Stage.GROUP

    val VALID_TIER: Tier = Tier.OVERUSED
    val INVALID_TIER: Tier = Tier.ANYTHING_GOES

    const val VALID_STD_DEV: Int = 5
    const val INVALID_STD_DEV: Int = -5

    const val VALID_K_FACTOR: Int = 32
    const val INVALID_K_FACTOR: Int = 0

    val VALID_STARTED_ON: LocalDateTime = LocalDateTime.now()
    val INVALID_STARTED_ON: LocalDateTime = LocalDateTime.MAX

    const val VALID_MOVE_NAME: String = "Explosion"
    const val INVALID_MOVE_NAME: String = "Thunder"

    val VALID_TYPE: Type = Type.NORMAL
    val INVALID_TYPE: Type = Type.ELECTRIC

    val VALID_DAMAGE: Damage = Damage.PHYSICAL
    val INVALID_DAMAGE: Damage = Damage.SPECIAL

    val VALID_ACCURACY: Int? = 100
    val INVALID_ACCURACY: Int? = -5

    const val VALID_PP: Int = 30
    const val INVALID_PP: Int = -10

    val VALID_MOVE_EFFECT: String? = "Inflicts damage, and causes the user to faint. Though its power is listed as 170, the target's Defense will be halved during damage calculation (unless it is at a value of 1), effectively doubling the power to 340."
    val INVALID_MOVE_EFFECT: String? = "Deals damage and has a 10% chance of paralyzing the target. Thunder cannot paralyze Electric-type Pokémon."

    const val VALID_POKEMON_NUMBER: String = "25"
    const val INVALID_POKEMON_NUMBER: String = "999"

    const val VALID_POKEMON_NAME: String = "Pikachu"
    const val INVALID_POKEMON_NAME: String = "Gorochu"

    val VALID_POKEMON_FORM: String? = "Alola"
    val INVALID_POKEMON_FORM: String? = "Unova"

    const val VALID_IS_LEGENDARY: Boolean = false
    const val INVALID_IS_LEGENDARY: Boolean = true

    const val VALID_IS_MYTHICAL: Boolean = false
    const val INVALID_IS_MYTHICAL: Boolean = true

    const val VALID_IS_MEGA: Boolean = false
    const val INVALID_IS_MEGA: Boolean = true

    val VALID_ALTERNATE_ABILITY: String? = "Static"
    val INVALID_ALTERNATE_ABILITY: String? = "Water Absorb"

    val VALID_HIDDEN_ABILITY: String? = "Lightning Rod"
    val INVALID_HIDDEN_ABILITY: String? = "Levitate"

    val VALID_GENERATION: Generation = Generation.KANTO
    val INVALID_GENERATION: Generation = Generation.GALAR

    val VALID_SUBTYPE: Type? = null
    val INVALID_SUBTYPE: Type? = Type.FIRE

    const val VALID_POKEMON_STAGE: Int = 1
    const val INVALID_POKEMON_STAGE: Int = 0

    const val VALID_SPRITE_URI: String = "pikachu.png"
    const val INVALID_SPRITE_URI: String = "gorochu.txt"

    const val VALID_PRIZE_NAME: String = "Beacon Badge"
    const val INVALID_PRIZE_NAME: String = "Basic Badge"

    const val VALID_IMAGE_URL: String = "beacon-badge.png"
    const val INVALID_IMAGE_URL: String = "basic-badge.txt"

    const val VALID_HAS_CONCLUDED: Boolean = false
    const val INVALID_HAS_CONCLUDED: Boolean = true

    val VALID_TO_BE_FOUGHT_ON: LocalDateTime = LocalDateTime.now()
    val INVALID_TO_BE_FOUGHT_ON: LocalDateTime = LocalDateTime.MAX

    val VALID_TITLE: Title = Title.ELITE_FOUR
    val INVALID_TITLE: Title = Title.LEAGUE_CHAMPION

    val VALID_NATURE: Nature = Nature.ADAMANT
    val INVALID_NATURE: Nature = Nature.BOLD

    const val VALID_HAPPINESS: Int = 255
    const val INVALID_HAPPINESS: Int = -1

    const val VALID_IS_SHINY: Boolean = false
    const val INVALID_IS_SHINY: Boolean = true

    const val VALID_LEVEL: Int = 100
    const val INVALID_LEVEL: Int = -1

    val VALID_RANK: Rank = Rank.LEAGUE_CHALLENGER
    val INVALID_RANK: Rank = Rank.ASCENDANT

    const val VALID_ELO: Int = 1000
    const val INVALID_ELO: Int = 999

    const val VALID_TRAINER_TEAM_NAME: String = "Champion-Level-001"
    const val INVALID_TRAINER_TEAM_NAME: String = "Ash's Team"

    val VALID_WON_ON: LocalDateTime = LocalDateTime.now()
    val INVALID_WON_ON: LocalDateTime = LocalDateTime.MAX

    val VALID_LOST_ON: LocalDateTime? = null
    val INVALID_LOST_ON: LocalDateTime? = LocalDateTime.now()

    const val VALID_TRAINER_USER_NAME: String = "Shoo"
    const val INVALID_TRAINER_USER_NAME: String = "Candy"

    const val VALID_TRAINER_USER_EMAIL: String = "shoo@neverending.com"
    const val INVALID_TRAINER_USER_EMAIL: String = "candy@howdareya.com"

    const val VALID_TRAINER_USER_PASSWORD_HASH: String = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8"
    const val INVALID_TRAINER_USER_PASSWORD_HASH: String = "1696c27ef5a17b20609e4a3e99a677ade376906d2d1a4c3613662f3106d2247e"

    const val VALID_TRAINER_USER_PASSWORD_SALT: String = "63479ad69a090b258277ec8fba6f99419a2ffb248981510657c944ccd1148e97"
    const val INVALID_TRAINER_USER_PASSWORD_SALT: String = "8cbbcf29d9cef89675c5f5c1dcfe827d0570416a5aaba30dd0de159661ad905b"

    const val VALID_TRAINER_USER_OAUTH_ID: String = "4c71867c-4389-439b-ba98-62202fb5dcbf"
    const val INVALID_TRAINER_USER_OAUTH_ID: String = "7b538418-9532-4df8-a77c-b564f2a446cf"

    val VALID_BATTLE_RESULT: BattleResult = BattleResult.WIN
    val INVALID_BATTLE_RESULT: BattleResult = BattleResult.LOSS

    val VALID_BATTLE_VERIFICATION_TYPE: BattleVerificationType = BattleVerificationType.TITLE
    val INVALID_BATTLE_VERIFICATION_TYPE: BattleVerificationType = BattleVerificationType.WILD

    val VALID_STAT: Stat = Stat.HP
    val INVALID_STAT: Stat = Stat.SPEED

    const val VALID_PRIZE_MIN: Int = 3
    const val INVALID_PRIZE_MIN: Int = -1

    const val VALID_TRAINER_POKEMON_NAME: String = "BabyShark"
    const val INVALID_TRAINER_POKEMON_NAME: String = "Canjy"

}
