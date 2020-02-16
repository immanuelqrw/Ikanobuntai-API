package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.entity.*
import com.immanuelqrw.ikanobuntai.api.service.TestConstants as C

object TestEntityConstants {

    val VALID_ABILITY: Ability = Ability(
        name = C.VALID_ABILITY_NAME,
        effect = C.VALID_ABILITY_EFFECT
    )

    val INVALID_ABILITY: Ability = Ability(
        name = C.INVALID_ABILITY_NAME,
        effect = C.INVALID_ABILITY_EFFECT
    )

    val VALID_BASE_STAT: BaseStat = BaseStat(
        total = C.VALID_TOTAL_BASE_STAT,
        hp = C.VALID_HP_BASE_STAT,
        attack = C.VALID_ATTACK_BASE_STAT,
        defense = C.VALID_DEFENSE_BASE_STAT,
        specialAttack = C.VALID_SPECIAL_ATTACK_BASE_STAT,
        specialDefense = C.VALID_SPECIAL_DEFENSE_BASE_STAT,
        speed = C.VALID_SPEED_BASE_STAT
    )

    val INVALID_BASE_STAT: BaseStat = BaseStat(
        total = C.INVALID_TOTAL_BASE_STAT,
        hp = C.INVALID_HP_BASE_STAT,
        attack = C.INVALID_ATTACK_BASE_STAT,
        defense = C.INVALID_DEFENSE_BASE_STAT,
        specialAttack = C.INVALID_SPECIAL_ATTACK_BASE_STAT,
        specialDefense = C.INVALID_SPECIAL_DEFENSE_BASE_STAT,
        speed = C.INVALID_SPEED_BASE_STAT
    )
    
    val VALID_TRAINER_USER: TrainerUser = TrainerUser(
        name = C.VALID_TRAINER_USER_NAME,
        email = C.VALID_TRAINER_USER_EMAIL,
        passwordHash = C.VALID_TRAINER_USER_PASSWORD_HASH,
        passwordSalt = C.VALID_TRAINER_USER_PASSWORD_SALT,
        oAuthId = C.VALID_TRAINER_USER_OAUTH_ID
    )

    val INVALID_TRAINER_USER: TrainerUser = TrainerUser(
        name = C.INVALID_TRAINER_USER_NAME,
        email = C.INVALID_TRAINER_USER_EMAIL,
        passwordHash = C.INVALID_TRAINER_USER_PASSWORD_HASH,
        passwordSalt = C.INVALID_TRAINER_USER_PASSWORD_SALT,
        oAuthId = C.INVALID_TRAINER_USER_OAUTH_ID
    )
    
    val VALID_TRAINER: Trainer = Trainer(
        name = C.VALID_TRAINER_NAME,
        trainerUser = VALID_TRAINER_USER
    )

    val INVALID_TRAINER: Trainer = Trainer(
        name = C.INVALID_TRAINER_NAME,
        trainerUser = INVALID_TRAINER_USER
    )

    val VALID_LEAGUE: League = League(
        name = C.VALID_LEAGUE_NAME,
        type = C.VALID_LEAGUE_TYPE,
        stage = C.VALID_STAGE,
        tier = C.VALID_TIER,
        stdDev = C.VALID_STD_DEV,
        kFactor = C.VALID_K_FACTOR,
        startedOn = C.VALID_STARTED_ON,
        prizeMin = C.VALID_PRIZE_MIN
    )

    val INVALID_LEAGUE: League = League(
        name = C.INVALID_LEAGUE_NAME,
        type = C.INVALID_LEAGUE_TYPE,
        stage = C.INVALID_STAGE,
        tier = C.INVALID_TIER,
        stdDev = C.INVALID_STD_DEV,
        kFactor = C.INVALID_K_FACTOR,
        startedOn = C.INVALID_STARTED_ON,
        prizeMin = C.INVALID_PRIZE_MIN
    )

    val VALID_BATTLE: Battle = Battle(
        type = C.VALID_BATTLE_TYPE,
        defender = VALID_TRAINER,
        challenger = INVALID_TRAINER,
        winner = VALID_TRAINER,
        league = VALID_LEAGUE,
        foughtOn = C.VALID_FOUGHT_ON
    )

    val INVALID_BATTLE: Battle = Battle(
        type = C.INVALID_BATTLE_TYPE,
        defender = INVALID_TRAINER,
        challenger = VALID_TRAINER,
        winner = INVALID_TRAINER,
        league = INVALID_LEAGUE,
        foughtOn = C.INVALID_FOUGHT_ON
    )
    
    val VALID_LEAGUE_FORMAT: LeagueFormat = LeagueFormat(
        league = VALID_LEAGUE,
        format = C.VALID_FORMAT
    )

    val INVALID_LEAGUE_FORMAT: LeagueFormat = LeagueFormat(
        league = INVALID_LEAGUE,
        format = C.INVALID_FORMAT
    )
    
    val VALID_CONFIGURATION: Configuration = Configuration(
        leagueFormat = VALID_LEAGUE_FORMAT,
        value = C.VALID_VALUE,
        type = C.VALID_CONFIGURATION_TYPE
    )

    val INVALID_CONFIGURATION: Configuration = Configuration(
        leagueFormat = INVALID_LEAGUE_FORMAT,
        value = C.INVALID_VALUE,
        type = C.INVALID_CONFIGURATION_TYPE
    )
    
    val VALID_EFFORT_VALUE: EffortValue = EffortValue(
        hp = C.VALID_HP_EFFORT_VALUE,
        attack = C.VALID_ATTACK_EFFORT_VALUE,
        defense = C.VALID_DEFENSE_EFFORT_VALUE,
        specialAttack = C.VALID_SPECIAL_ATTACK_EFFORT_VALUE,
        specialDefense = C.VALID_SPECIAL_DEFENSE_EFFORT_VALUE,
        speed = C.VALID_SPEED_EFFORT_VALUE
    )

    val INVALID_EFFORT_VALUE: EffortValue = EffortValue(
        hp = C.INVALID_HP_EFFORT_VALUE,
        attack = C.INVALID_ATTACK_EFFORT_VALUE,
        defense = C.INVALID_DEFENSE_EFFORT_VALUE,
        specialAttack = C.INVALID_SPECIAL_ATTACK_EFFORT_VALUE,
        specialDefense = C.INVALID_SPECIAL_DEFENSE_EFFORT_VALUE,
        speed = C.INVALID_SPEED_EFFORT_VALUE
    )

    val VALID_INDIVIDUAL_VALUE: IndividualValue = IndividualValue(
        hp = C.VALID_HP_INDIVIDUAL_VALUE,
        attack = C.VALID_ATTACK_INDIVIDUAL_VALUE,
        defense = C.VALID_DEFENSE_INDIVIDUAL_VALUE,
        specialAttack = C.VALID_SPECIAL_ATTACK_INDIVIDUAL_VALUE,
        specialDefense = C.VALID_SPECIAL_DEFENSE_INDIVIDUAL_VALUE,
        speed = C.VALID_SPEED_INDIVIDUAL_VALUE
    )

    val INVALID_INDIVIDUAL_VALUE: IndividualValue = IndividualValue(
        hp = C.INVALID_HP_INDIVIDUAL_VALUE,
        attack = C.INVALID_ATTACK_INDIVIDUAL_VALUE,
        defense = C.INVALID_DEFENSE_INDIVIDUAL_VALUE,
        specialAttack = C.INVALID_SPECIAL_ATTACK_INDIVIDUAL_VALUE,
        specialDefense = C.INVALID_SPECIAL_DEFENSE_INDIVIDUAL_VALUE,
        speed = C.INVALID_SPEED_INDIVIDUAL_VALUE
    )
    
    val VALID_ITEM: Item = Item(
        name = C.VALID_ITEM_NAME,
        effect = C.VALID_ITEM_EFFECT
    )

    val INVALID_ITEM: Item = Item(
        name = C.INVALID_ITEM_NAME,
        effect = C.INVALID_ITEM_EFFECT
    )
    
    val VALID_POKEMON: Pokemon = Pokemon(
        number = C.VALID_POKEMON_NUMBER,
        name = C.VALID_POKEMON_NAME,
        form = C.VALID_POKEMON_FORM,
        isLegendary = C.VALID_IS_LEGENDARY,
        isMythical = C.VALID_IS_MYTHICAL,
        isMega = C.VALID_IS_MEGA
    )

    val INVALID_POKEMON: Pokemon = Pokemon(
        number = C.INVALID_POKEMON_NUMBER,
        name = C.INVALID_POKEMON_NAME,
        form = C.INVALID_POKEMON_FORM,
        isLegendary = C.INVALID_IS_LEGENDARY,
        isMythical = C.INVALID_IS_MYTHICAL,
        isMega = C.INVALID_IS_MEGA
    )

    val VALID_POKEMON_ABILITY: PokemonAbility = PokemonAbility(
        main = VALID_ABILITY,
        alternate = null,
        hidden = null
    )

    val INVALID_POKEMON_ABILITY: PokemonAbility = PokemonAbility(
        main = INVALID_ABILITY,
        alternate = null,
        hidden = null
    )

    val VALID_POKEMON_GENERATION: PokemonGeneration = PokemonGeneration(
        pokemon = VALID_POKEMON,
        generation = C.VALID_GENERATION,
        mainType = C.VALID_TYPE,
        subType = C.VALID_SUBTYPE,
        stage = C.VALID_POKEMON_STAGE,
        prevolvedPokemon = null,
        baseStat = VALID_BASE_STAT,
        ability = VALID_POKEMON_ABILITY,
        spriteUri = C.VALID_SPRITE_URI
    )

    val INVALID_POKEMON_GENERATION: PokemonGeneration = PokemonGeneration(
        pokemon = INVALID_POKEMON,
        generation = C.INVALID_GENERATION,
        mainType = C.INVALID_TYPE,
        subType = C.INVALID_SUBTYPE,
        stage = C.INVALID_POKEMON_STAGE,
        prevolvedPokemon = null,
        baseStat = INVALID_BASE_STAT,
        ability = INVALID_POKEMON_ABILITY,
        spriteUri = C.INVALID_SPRITE_URI
    )
    
    val VALID_MOVE: Move = Move(
        name = C.VALID_MOVE_NAME,
        type = C.VALID_TYPE,
        damage = C.VALID_DAMAGE,
        accuracy = C.VALID_ACCURACY,
        pp = C.VALID_PP,
        effect = C.VALID_MOVE_EFFECT
    )

    val INVALID_MOVE: Move = Move(
        name = C.INVALID_MOVE_NAME,
        type = C.INVALID_TYPE,
        damage = C.INVALID_DAMAGE,
        accuracy = C.INVALID_ACCURACY,
        pp = C.INVALID_PP,
        effect = C.INVALID_MOVE_EFFECT
    )

    val VALID_TRAINER_POKEMON: TrainerPokemon = TrainerPokemon(
        name = C.VALID_TRAINER_POKEMON_NAME,
        pokemonGeneration = VALID_POKEMON_GENERATION,
        trainer = VALID_TRAINER,
        nature = C.VALID_NATURE,
        ability = VALID_ABILITY,
        individualValue = VALID_INDIVIDUAL_VALUE,
        effortValue = VALID_EFFORT_VALUE,
        move1 = VALID_MOVE,
        move2 = null,
        move3 = null ,
        move4 = null,
        happiness = C.VALID_HAPPINESS,
        isShiny = C.VALID_IS_SHINY,
        level = C.VALID_LEVEL,
        item = VALID_ITEM
    )

    val INVALID_TRAINER_POKEMON: TrainerPokemon = TrainerPokemon(
        name = C.INVALID_TRAINER_POKEMON_NAME,
        pokemonGeneration = INVALID_POKEMON_GENERATION,
        trainer = INVALID_TRAINER,
        nature = C.INVALID_NATURE,
        ability = INVALID_ABILITY,
        individualValue = INVALID_INDIVIDUAL_VALUE,
        effortValue = INVALID_EFFORT_VALUE,
        move1 = INVALID_MOVE,
        move2 = null,
        move3 = null ,
        move4 = null,
        happiness = C.INVALID_HAPPINESS,
        isShiny = C.INVALID_IS_SHINY,
        level = C.INVALID_LEVEL,
        item = INVALID_ITEM
    )

    val VALID_LEAGUE_POKEMON: LeaguePokemon = LeaguePokemon(
        trainerPokemon = VALID_TRAINER_POKEMON,
        league = VALID_LEAGUE
    )

    val INVALID_LEAGUE_POKEMON: LeaguePokemon = LeaguePokemon(
        trainerPokemon = INVALID_TRAINER_POKEMON,
        league = INVALID_LEAGUE
    )
    
    val VALID_LEAGUE_TRAINER: LeagueTrainer = LeagueTrainer(
        league = VALID_LEAGUE,
        trainer = VALID_TRAINER
    )

    val INVALID_LEAGUE_TRAINER: LeagueTrainer = LeagueTrainer(
        league = INVALID_LEAGUE,
        trainer = INVALID_TRAINER
    )
    
    val VALID_TRAINER_TEAM: TrainerTeam = TrainerTeam(
        name = C.VALID_TRAINER_TEAM_NAME,
        trainer = VALID_TRAINER
    )

    val INVALID_TRAINER_TEAM: TrainerTeam = TrainerTeam(
        name = C.INVALID_TRAINER_TEAM_NAME,
        trainer = INVALID_TRAINER
    )
    
    val VALID_POKEMON_TEAM: PokemonTeam = PokemonTeam(
        trainerTeam = VALID_TRAINER_TEAM,
        trainerPokemon = VALID_TRAINER_POKEMON
    )

    val INVALID_POKEMON_TEAM: PokemonTeam = PokemonTeam(
        trainerTeam = INVALID_TRAINER_TEAM,
        trainerPokemon = INVALID_TRAINER_POKEMON
    )
    
    val VALID_POKEMON_TIER: PokemonTier = PokemonTier(
        pokemon = VALID_POKEMON,
        generation = C.VALID_GENERATION,
        tier = C.VALID_TIER
    )

    val INVALID_POKEMON_TIER: PokemonTier = PokemonTier(
        pokemon = INVALID_POKEMON,
        generation = C.INVALID_GENERATION,
        tier = C.INVALID_TIER
    )
    
    val VALID_PRIZE: Prize = Prize(
        name = C.VALID_PRIZE_NAME,
        imageUrl = C.VALID_IMAGE_URL
    )

    val INVALID_PRIZE: Prize = Prize(
        name = C.INVALID_PRIZE_NAME,
        imageUrl = C.INVALID_IMAGE_URL
    )
    
    val VALID_TIER_TITLE: TierTitle = TierTitle(
        tier = C.VALID_TIER,
        title = C.VALID_TITLE,
        prize = VALID_PRIZE
    )

    val INVALID_TIER_TITLE: TierTitle = TierTitle(
        tier = C.INVALID_TIER,
        title = C.INVALID_TITLE,
        prize = INVALID_PRIZE
    )
    
    val VALID_PRIZE_GRUNT: PrizeGrunt = PrizeGrunt(
        trainer = VALID_TRAINER,
        tierTitle = VALID_TIER_TITLE,
        league = VALID_LEAGUE
    )

    val INVALID_PRIZE_GRUNT: PrizeGrunt = PrizeGrunt(
        trainer = INVALID_TRAINER,
        tierTitle = INVALID_TIER_TITLE,
        league = INVALID_LEAGUE
    )
    
    val VALID_SCHEDULED_BATTLE: ScheduledBattle = ScheduledBattle(
        type = C.VALID_BATTLE_TYPE,
        defender = VALID_TRAINER,
        challenger = INVALID_TRAINER,
        league = VALID_LEAGUE,
        toBeFoughtOn = C.VALID_TO_BE_FOUGHT_ON,
        hasConcluded = C.VALID_HAS_CONCLUDED
    )

    val INVALID_SCHEDULED_BATTLE: ScheduledBattle = ScheduledBattle(
        type = C.INVALID_BATTLE_TYPE,
        defender = INVALID_TRAINER,
        challenger = VALID_TRAINER,
        league = INVALID_LEAGUE,
        toBeFoughtOn = C.INVALID_TO_BE_FOUGHT_ON,
        hasConcluded = C.INVALID_HAS_CONCLUDED
    )
    
    val VALID_TRAINER_PRIZE: TrainerPrize = TrainerPrize(
        trainer = VALID_TRAINER,
        prize = VALID_PRIZE,
        league = VALID_LEAGUE
    )

    val INVALID_TRAINER_PRIZE: TrainerPrize = TrainerPrize(
        trainer = INVALID_TRAINER,
        prize = INVALID_PRIZE,
        league = INVALID_LEAGUE
    )
    
    val VALID_TRAINER_RATING: TrainerRating = TrainerRating(
        trainer = VALID_TRAINER,
        tier = C.VALID_TIER,
        rank = C.VALID_RANK,
        elo = C.VALID_ELO
    )

    val INVALID_TRAINER_RATING: TrainerRating = TrainerRating(
        trainer = INVALID_TRAINER,
        tier = C.INVALID_TIER,
        rank = C.INVALID_RANK,
        elo = C.INVALID_ELO
    )
    
    val VALID_TRAINER_TITLE: TrainerTitle = TrainerTitle(
        trainer = VALID_TRAINER,
        tierTitle = VALID_TIER_TITLE,
        wonOn = C.VALID_WON_ON,
        lostOn = C.VALID_LOST_ON
    )

    val INVALID_TRAINER_TITLE: TrainerTitle = TrainerTitle(
        trainer = INVALID_TRAINER,
        tierTitle = INVALID_TIER_TITLE,
        wonOn = C.INVALID_WON_ON,
        lostOn = C.INVALID_LOST_ON
    )

}
