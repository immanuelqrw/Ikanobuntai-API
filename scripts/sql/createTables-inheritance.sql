
CREATE TABLE "TableBase" (
  "createdOn" TIMESTAMP NOT NULL,
  "modifiedOn" TIMESTAMP NOT NULL,
  "removedOn" TIMESTAMP
);

-- TRAINER tables

CREATE TYPE RANK AS ENUM(
  'JUNIOR_TRAINER',
  'TRAINER',
  'ACE_TRAINER',
  'LEAGUE_CHALLENGER',
  'LEAGUE_FINALIST',
  'ASCENDANT',
  'WORLD_CHALLENGER',
  'WORLD_RANKER',
  'MASTER'
);

CREATE TYPE TIER AS ENUM(
  'ANYTHING_GOES'
  'UBERS'
  'OVERUSED'
  'UNDERUSED'
  'RARELYUSED'
  'NEVERUSED'
  'PU'
  'LITTLE_CUP'
);

CREATE TYPE STAGE AS ENUM(
  'SERIES',
  'PRELIMINARY',
  'QUALIFIER',
  'GROUP',
  'ROUND_OF_64',
  'ROUND_OF_32',
  'ROUND_OF_16',
  'QUARTERFINALS',
  'SEMIFINALS',
  'FINAL',
  'CHAMPION_BATTLE'
);

CREATE TYPE TITLE AS ENUM(
  'GYM_LEADER',
  'LEAGUE_RANKED_#05',
  'ELITE_FOUR',
  'LEAGUE_BARON',
  'LEAGUE_CHAMPION',
  'ARENA_TYCOON',
  'DOME_ACE',
  'FACTORY_HEAD',
  'PALACE_MAVEN',
  'PIKE_QUEEN',
  'PYRAMID_KING',
  'SALON_MAIDEN',
  'TOWER_TYCOON',
  'ARCADE_STAR',
  'CASTLE_VALET',
  'HALL_MATRON',
  'WORLD_RANKED_#10',
  'WORLD_RANKED_#09',
  'WORLD_RANKED_#08',
  'WORLD_RANKED_#07',
  'WORLD_RANKED_#06',
  'WORLD_RANKED_#05',
  'WORLD_RANKED_#04',
  'WORLD_RANKED_#03',
  'WORLD_RANKED_#02',
  'WORLD_RANKED_#01',
  'WORLD_NOBLE',
  'WORLD_CHAMPION'
);

-- - Add seed data
CREATE TABLE "Prize" (
  "id" UUID PRIMARY KEY DEFAULT UUID_GENERATE_V4(),
  "name" VARCHAR(32) NOT NULL UNIQUE,
  "imageUrl" VARCHAR(256) NOT NULL
) INHERITS ("TableBase");

-- - Add seed data
-- - Consider adding name field for Gym Leaders
CREATE TABLE "TierTitle" (
  "id" UUID PRIMARY KEY DEFAULT UUID_GENERATE_V4(),
  "tier" TIER NOT NULL,
  "title" TITLE NOT NULL,
  "prizeId" UUID NOT NULL REFERENCES "Prize" ("id")
) INHERITS ("TableBase");


CREATE TABLE "PrizeGrunt" (
  "id" UUID PRIMARY KEY DEFAULT UUID_GENERATE_V4(),
  "trainerId" UUID NOT NULL REFERENCES "Trainer" ("id"),
  "tierTitleId" UUID NOT NULL REFERENCES "TierTitle" ("id"),
  "leagueId" UUID NOT NULL REFERENCES "League" ("id"),
  UNIQUE ("trainerId", "leagueId")
) INHERITS ("TableBase");


CREATE TYPE FORMAT AS ENUM(
  'BANNED_ITEM',
  'BANNED_POKEMON',
  'LEVEL_LIMIT',
  'MONOTYPE',
  'SHARED_MONOTYPE',
  'LEVEL_RANGE',
  'NO_MEGA',
  'NO_LEGENDARY',
  'NO_MYTHICAL',
  'TEAM_LEVEL_LIMIT'
);

CREATE TYPE LEAGUE_TYPE AS ENUM(
  'WILD',
  'LEAGUE',
  'TOURNAMENT',
  'BATTLE_FRONTIER',
  'WORLD_CHAMPIONSHIP'
);

CREATE TABLE "League" (
  "id" UUID PRIMARY KEY DEFAULT UUID_GENERATE_V4(),
  "name" VARCHAR(64) NOT NULL UNIQUE,
  "type" LEAGUE_TYPE NOT NULL,
  "stage" STAGE NOT NULL,
  "tier" TIER NOT NULL,
  "stdDev" SMALLINT NOT NULL DEFAULT 400,
  "kFactor" SMALLINT NOT NULL DEFAULT 32,
  "startedOn" TIMESTAMP NOT NULL,
  "prizeMin" SMALLINT NOT NULL
) INHERITS ("TableBase");

-- - Add seed data
CREATE TABLE "LeagueFormat" (
  "id" UUID PRIMARY KEY DEFAULT UUID_GENERATE_V4(),
  "leagueId" UUID NOT NULL REFERENCES "League" ("id"),
  "format" FORMAT NOT NULL,
  UNIQUE("leagueId", "format")
) INHERITS ("TableBase");

CREATE TYPE CONFIGURATION_TYPE AS ENUM(
  'BOOLEAN',
  'SCALAR',
  'STRING',
  'COLLECTION'
);

CREATE TABLE "Configuration" (
  "id" UUID PRIMARY KEY DEFAULT UUID_GENERATE_V4(),
  "leagueFormatId" UUID NOT NULL REFERENCES "LeagueFormat" ("id") UNIQUE,
  "value" VARCHAR(64) NOT NULL,
  "type" CONFIGURATION_TYPE NOT NULL
) INHERITS ("TableBase");

CREATE TABLE "TrainerUser" (
  "id" UUID PRIMARY KEY DEFAULT UUID_GENERATE_V4(),
  "name" VARCHAR(64) NOT NULL UNIQUE,
  "email" VARCHAR(300) NOT NULL UNIQUE,
  "passwordHash" CHAR(64) NOT NULL,
  "passwordSalt" CHAR(64) NOT NULL,
  "oAuthId" CHAR(64) NOT NULL
) INHERITS ("TableBase");

CREATE TABLE "Trainer" (
  "id" UUID PRIMARY KEY DEFAULT UUID_GENERATE_V4(),
  "name" VARCHAR(64) NOT NULL UNIQUE,
  "trainerUserId" UUID NOT NULL REFERENCES "TrainerUser" ("id")
) INHERITS ("TableBase");

CREATE TABLE "TrainerRating" (
  "id" UUID PRIMARY KEY DEFAULT UUID_GENERATE_V4(),
  "trainerId" UUID NOT NULL REFERENCES "TrainerUser" ("id"),
  "tier" TIER NOT NULL,
  "rank" RANK NOT NULL,
  "rating" SMALLINT NOT NULL DEFAULT 1000 CHECK ("rating" >= 1000),
  UNIQUE ("trainerId", "tier")
) INHERITS ("TableBase");

CREATE TABLE "TrainerTitle" (
  "id" UUID PRIMARY KEY DEFAULT UUID_GENERATE_V4(),
  "trainerId" UUID NOT NULL REFERENCES "Trainer" ("id"),
  "tierTitleId" UUID NOT NULL REFERENCES "TierTitle" ("id"),
  "wonOn" TIMESTAMP NOT NULL,
  "lostOn" TIMESTAMP
) INHERITS ("TableBase");

CREATE TYPE BATTLE_RESULT AS ENUM (
    'WIN',
    'DRAW',
    'LOSS'
);

CREATE TYPE BATTLE_TYPE AS ENUM(
  'NON_RANKED',
  'WILD',
  'LEAGUE',
  'TOURNAMENT',
  'GYM',
  'GYM_LEADER',
  'LEAGUE_FINALIST',
  'ELITE_FOUR',
  'LEAGUE_CHAMPION',
  'FRONTIER',
  'FRONTIER_BRAIN',
  'WORLD_FINALIST',
  'WORLD_CHAMPION'
);

CREATE TABLE "ScheduledBattle" (
  "id" UUID PRIMARY KEY DEFAULT UUID_GENERATE_V4(),
  "type" BATTLE_TYPE NOT NULL,
  "defenderId" UUID NOT NULL REFERENCES "Trainer" ("id"),
  "challengerId" UUID NOT NULL REFERENCES "Trainer" ("id"),
  "leagueId" UUID NOT NULL REFERENCES "League" ("id"),
  "toBeFoughtOn" TIMESTAMP NOT NULL ,
  "hasConcluded" BOOLEAN NOT NULL DEFAULT FALSE
  CHECK ("defenderId" <> "challengerId")
) INHERITS ("TableBase");

CREATE TABLE "Battle" (
  "id" UUID PRIMARY KEY DEFAULT UUID_GENERATE_V4(),
  "type" BATTLE_TYPE NOT NULL,
  "defenderId" UUID NOT NULL REFERENCES "Trainer" ("id"),
  "challengerId" UUID NOT NULL REFERENCES "Trainer" ("id"),
  "winnerId" UUID REFERENCES "Trainer" ("id"),
  "leagueId" UUID NOT NULL REFERENCES "League" ("id"),
  "foughtOn" TIMESTAMP NOT NULL,
  CHECK ("defenderId" <> "challengerId")
) INHERITS ("TableBase");

-- POKEMON tables

CREATE TABLE "Ability" (
  "id" UUID PRIMARY KEY DEFAULT UUID_GENERATE_V4(),
  "name" VARCHAR(32) NOT NULL UNIQUE,
  "effect" VARCHAR(256) NOT NULL
) INHERITS ("TableBase");

CREATE TYPE STAT AS ENUM (
  'HP',
  'ATTACK',
  'DEFENSE',
  'SPECIAL_ATTACK',
  'SPECIAL_DEFENSE',
  'SPEED'
);

CREATE TABLE "Nature" (
  "id" UUID PRIMARY KEY DEFAULT UUID_GENERATE_V4(),
  "name" VARCHAR(20) NOT NULL UNIQUE,
  "dropStat" STAT,
  "riseStat" STAT
) INHERITS ("TableBase");

CREATE TABLE "IndividualValue" (
  "id" UUID PRIMARY KEY DEFAULT UUID_GENERATE_V4(),
  "hp" SMALLINT NOT NULL CHECK ("hp" BETWEEN 0 AND 31),
  "attack" SMALLINT NOT NULL CHECK ("attack" BETWEEN 0 AND 31),
  "defense" SMALLINT NOT NULL CHECK ("defense" BETWEEN 0 AND 31),
  "specialAttack" SMALLINT NOT NULL CHECK ("specialAttack" BETWEEN 0 AND 31),
  "specialDefense" SMALLINT NOT NULL CHECK ("specialDefense" BETWEEN 0 AND 31),
  "speed" SMALLINT NOT NULL CHECK ("speed" BETWEEN 0 AND 31)
) INHERITS ("TableBase");

CREATE TABLE "EffortValue" (
  "id" UUID PRIMARY KEY DEFAULT UUID_GENERATE_V4(),
  "hp" SMALLINT NOT NULL CHECK ("hp" BETWEEN 0 AND 252),
  "attack" SMALLINT NOT NULL CHECK ("attack" BETWEEN 0 AND 252),
  "defense" SMALLINT NOT NULL CHECK ("defense" BETWEEN 0 AND 252),
  "specialAttack" SMALLINT NOT NULL CHECK ("specialAttack" BETWEEN 0 AND 252),
  "specialDefense" SMALLINT NOT NULL CHECK ("specialDefense" BETWEEN 0 AND 252),
  "speed" SMALLINT NOT NULL CHECK ("speed" BETWEEN 0 AND 252),
  CHECK ("hp" + "attack" + "defense" + "specialAttack" + "specialDefense" + "speed" <= 510)
) INHERITS ("TableBase");

CREATE TYPE POKETYPE AS ENUM (
  'GRASS',
  'FIRE',
  'WATER',
  'ELECTRIC',
  'NORMAL',
  'GROUND',
  'ROCK',
  'FIGHTING',
  'FLYING',
  'ICE',
  'POISON',
  'BUG',
  'PSYCHIC',
  'GHOST',
  'DRAGON',
  'DARK',
  'STEEL',
  'FAIRY'
);


CREATE TYPE GENERATION AS ENUM(
    'KANTO',
    'JOHTO',
    'HOENN',
    'SINNOH',
    'UNOVA',
    'KALOS',
    'ALOLA',
    'GALAR'
);

-- - Add smogon tier generation connection -- separate join table with generation
CREATE TABLE "Pokemon" (
  "id" UUID PRIMARY KEY DEFAULT UUID_GENERATE_V4(),
  "number" SMALLINT NOT NULL,
  "name" VARCHAR(32) NOT NULL,
  "form" VARCHAR(32),
  "isLegendary" BOOLEAN NOT NULL DEFAULT FALSE,
  "isMythical" BOOLEAN NOT NULL DEFAULT FALSE,
  "isMega" BOOLEAN NOT NULL DEFAULT FALSE,
  UNIQUE ("name", "form")
) INHERITS ("TableBase");

CREATE TABLE "PokemonGeneration" (
  "id" UUID PRIMARY KEY DEFAULT UUID_GENERATE_V4(),
  "pokemonId" UUID NOT NULL REFERENCES "Pokemon" ("id"),
  "generation" GENERATION NOT NULL,
  "mainType" POKETYPE NOT NULL,
  "subType" POKETYPE,
  "stage" SMALLINT NOT NULL DEFAULT 1,
  "prevolvedPokemonId" UUID REFERENCES "Pokemon" ("id"),
  "baseStatTotal" SMALLINT NOT NULL CHECK("baseStatTotal" BETWEEN 0 AND 1530),
  "hpBaseStat" SMALLINT NOT NULL CHECK ("hpBaseStat" BETWEEN 0 AND 255),
  "attackBaseStat" SMALLINT NOT NULL CHECK ("attackBaseStat" BETWEEN 0 AND 255),
  "defenseBaseStat" SMALLINT NOT NULL CHECK ("defenseBaseStat" BETWEEN 0 AND 255),
  "specialAttackBaseStat" SMALLINT NOT NULL CHECK ("specialAttackBaseStat" BETWEEN 0 AND 255),
  "specialDefenseBaseStat" SMALLINT NOT NULL CHECK ("specialDefenseBaseStat" BETWEEN 0 AND 255),
  "speedBaseStat" SMALLINT NOT NULL CHECK ("speedBaseStat" BETWEEN 0 AND 255),
  "abilityId" UUID NOT NULL REFERENCES "Ability" ("id"),
  "alternateAbilityId" UUID REFERENCES "Ability" ("id"),
  "hiddenAbilityId" UUID REFERENCES "Ability" ("id"),
  "spriteUri" VARCHAR(256) NOT NULL
) INHERITS ("TableBase");

CREATE TABLE "PokemonTier" (
  "id" UUID PRIMARY KEY DEFAULT UUID_GENERATE_V4(),
  "pokemonId" UUID NOT NULL REFERENCES "Pokemon" ("id"),
  "generation" GENERATION NOT NULL,
  "tier" TIER NOT NULL
) INHERITS ("TableBase");

CREATE TABLE "Item" (
  "id" UUID PRIMARY KEY DEFAULT UUID_GENERATE_V4(),
  "name" VARCHAR(64) NOT NULL UNIQUE,
  "effect" VARCHAR(128) NOT NULL
) INHERITS ("TableBase");


CREATE TYPE DAMAGE AS ENUM(
  'PHYSICAL',
  'SPECIAL',
  'STATUS'
);

CREATE TABLE "Move" (
  "id" UUID PRIMARY KEY DEFAULT UUID_GENERATE_V4(),
  "name" VARCHAR(32) NOT NULL UNIQUE,
  "type" POKETYPE NOT NULL,
  "damage" DAMAGE NOT NULL,
  "accuracy" SMALLINT CHECK ("accuracy" BETWEEN 0 AND 100),
  "pp" SMALLINT NOT NULL CHECK ("pp" BETWEEN 1 AND 56),
  "effect" VARCHAR(128)
) INHERITS ("TableBase");

CREATE TABLE "TrainerPokemon" (
  "id" UUID PRIMARY KEY DEFAULT UUID_GENERATE_V4(),
  "name" VARCHAR(32),
  "pokemonGenerationId" UUID NOT NULL REFERENCES "PokemonGeneration" ("id"),
  "trainerId" UUID NOT NULL REFERENCES "Trainer" ("id"),
  "natureId" UUID NOT NULL REFERENCES "Nature" ("id"),
  "abilityId" UUID NOT NULL REFERENCES "Ability" ("id"),
  "individualValueId" UUID NOT NULL REFERENCES "IndividualValue" ("id"),
  "effortValueId" UUID NOT NULL REFERENCES "EffortValue" ("id"),
  "move1Id" UUID NOT NULL REFERENCES "Move" ("id"),
  "move2Id" UUID REFERENCES "Move" ("id"),
  "move3Id" UUID REFERENCES "Move" ("id"),
  "move4Id" UUID REFERENCES "Move" ("id"),
  "happiness" SMALLINT NOT NULL DEFAULT 255 CHECK ("happiness" BETWEEN 0 AND 255),
  "isShiny" BOOLEAN NOT NULL DEFAULT FALSE,
  "level" SMALLINT NOT NULL DEFAULT 100 CHECK ("level" BETWEEN 0 AND 100),
  "itemId" UUID NOT NULL REFERENCES "Item" ("id")
) INHERITS ("TableBase");

CREATE TABLE "TrainerPrize" (
  "id" UUID PRIMARY KEY DEFAULT UUID_GENERATE_V4(),
  "trainerId" UUID NOT NULL REFERENCES "TierTitle" ("id"),
  "prizeId" UUID NOT NULL REFERENCES "Prize" ("id"),
  "leagueId" UUID NOT NULL REFERENCES "League" ("id")
) INHERITS ("TableBase");

-- - Enforce limitation on amount of registered pokemon for each league
CREATE TABLE "LeaguePokemon" (
  "id" UUID PRIMARY KEY DEFAULT UUID_GENERATE_V4(),
  "trainerPokemonId" UUID NOT NULL REFERENCES "TrainerPokemon" ("id"),
  "leagueId" UUID NOT NULL REFERENCES "League" ("id")
) INHERITS ("TableBase");

CREATE TABLE "LeagueTrainer" (
  "id" UUID PRIMARY KEY DEFAULT UUID_GENERATE_V4(),
  "leagueId" UUID NOT NULL REFERENCES "League" ("id"),
  "trainerId" UUID NOT NULL REFERENCES "Trainer" ("id"),
  UNIQUE ("leagueId", "trainerId")
) INHERITS ("TableBase");

CREATE TABLE "TrainerTeam" (
  "id" UUID PRIMARY KEY DEFAULT UUID_GENERATE_V4(),
  "name" VARCHAR(32) NOT NULL UNIQUE,
  "trainerId" UUID NOT NULL REFERENCES "Trainer" ("id")
) INHERITS ("TableBase");

CREATE TABLE "PokemonTeam" (
  "id" UUID PRIMARY KEY DEFAULT UUID_GENERATE_V4(),
  "trainerTeamId" UUID NOT NULL REFERENCES "TrainerTeam" ("id"),
  "trainerPokemonId" UUID NOT NULL REFERENCES "TrainerPokemon" ("id"),
  UNIQUE("trainerTeamId", "trainerPokemonId")
) INHERITS ("TableBase");
