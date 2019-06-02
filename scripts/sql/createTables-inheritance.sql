
CREATE TABLE "TableBase" (
  "createdOn" TIMESTAMP NOT NULL,
  "modifiedOn" TIMESTAMP NOT NULL,
  "removedOn" TIMESTAMP
);

-- TRAINER tables

CREATE TABLE "Elo" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "divider" SMALLINT NOT NULL,
  "k" SMALLINT NOT NULL
) INHERITS ("TableBase");

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

CREATE TABLE "TierTitle" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "tier" TIER NOT NULL,
  "title" TITLE NOT NULL
) INHERITS ("TableBase");

CREATE TABLE "Configuration" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "name" VARCHAR(64) NOT NULL,
  "value" VARCHAR(64) NOT NULL,
  "type" VARCHAR(32) NOT NULL,
  "tier" TIER NOT NULL,
  CONSTRAINT "uq_name_tier" UNIQUE ("name", "tier")
) INHERITS ("TableBase");

CREATE TABLE "Format" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "name" VARCHAR(64) NOT NULL UNIQUE,
  "description" VARCHAR(256) NOT NULL,
  "hasRuleset" BOOLEAN NOT NULL DEFAULT FALSE
) INHERITS ("TableBase");

CREATE TYPE LEAGUE_TYPE AS ENUM(
  'WILD',
  'GYM',
  'LEAGUE',
  'BATTLE_FRONTIER',
  'WORLD_CHAMPIONSHIP'
);

CREATE TABLE "League" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "name" VARCHAR(64) NOT NULL UNIQUE,
  "type" LEAGUE_TYPE NOT NULL,
  "stage" STAGE NOT NULL,
  "tier" TIER NOT NULL,
  "eloId" BIGINT NOT NULL REFERENCES "Elo" ("id")
) INHERITS ("TableBase");

CREATE TABLE "LeagueFormat" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "leagueId" BIGINT NOT NULL REFERENCES "League" ("id"),
  "formatId" BIGINT NOT NULL REFERENCES "Format" ("id")
) INHERITS ("TableBase");

CREATE TABLE "TrainerUser" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "name" VARCHAR(64) NOT NULL UNIQUE,
  "email" VARCHAR(300) NOT NULL UNIQUE,
  "passwordHash" CHAR(64) NOT NULL,
  "passwordSalt" CHAR(64) NOT NULL,
  "oAuthId" CHAR(64) NOT NULL
) INHERITS ("TableBase");

CREATE TABLE "Trainer" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "name" VARCHAR(64) NOT NULL UNIQUE,
  "trainerUserId" BIGINT NOT NULL REFERENCES "TrainerUser" ("id"),
  "tier" TIER NOT NULL,
  "rank" RANK NOT NULL,
  "rating" SMALLINT NOT NULL DEFAULT 1000 CHECK ("rating" >= 1000)
) INHERITS ("TableBase");

CREATE TABLE "TrainerTitle" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "trainerId" BIGINT NOT NULL REFERENCES "Trainer" ("id"),
  "titleId" BIGINT NOT NULL REFERENCES "TierTitle" ("id"),
  "wonOn" TIMESTAMP NOT NULL,
  "lostOn" TIMESTAMP
) INHERITS ("TableBase");

CREATE TABLE "BattleRating" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "defenderTitleId" BIGINT NOT NULL REFERENCES "TierTitle" ("id"),
  "challengerTitleId" BIGINT NOT NULL REFERENCES "TierTitle" ("id"),
  "leagueId" BIGINT NOT NULL REFERENCES "League" ("id"),
  "value" SMALLINT NOT NULL,
  CHECK ("defenderTitleId" <> "challengerTitleId")
) INHERITS ("TableBase");

CREATE TYPE BATTLE_RESULT AS ENUM (
    'WIN',
    'DRAW',
    'LOSS'
);

CREATE TYPE BATTLE_TYPE AS ENUM(
  'NON_RANKED',
  'WILD',
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

CREATE TABLE "Battle" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "type" BATTLE_TYPE NOT NULL,
  "defenderId" BIGINT NOT NULL REFERENCES "Trainer" ("id"),
  "challengerId" BIGINT NOT NULL REFERENCES "Trainer" ("id"),
  "winnerId" BIGINT REFERENCES "Trainer" ("id"),
  "rank" RANK NOT NULL,
  "value" SMALLINT NOT NULL,
  "foughtOn" TIMESTAMP NOT NULL,
  CHECK ("defenderId" <> "challengerId")
) INHERITS ("TableBase");

-- POKEMON tables

CREATE TABLE "Ability" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "name" VARCHAR(32) NOT NULL UNIQUE,
  "effect" VARCHAR(256) NOT NULL
) INHERITS ("TableBase");

CREATE TYPE STAT AS ENUM (
  'HP',
  'Attack',
  'Defense',
  'SpecialAttack',
  'SpecialDefense',
  'Speed'
);

CREATE TABLE "Nature" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "name" VARCHAR(20) NOT NULL UNIQUE,
  "dropStat" STAT,
  "riseStat" STAT
) INHERITS ("TableBase");

CREATE TABLE "IndividualValue" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "hp" SMALLINT NOT NULL CHECK ("hp" BETWEEN 0 AND 31),
  "attack" SMALLINT NOT NULL CHECK ("attack" BETWEEN 0 AND 31),
  "defense" SMALLINT NOT NULL CHECK ("defense" BETWEEN 0 AND 31),
  "specialAttack" SMALLINT NOT NULL CHECK ("specialAttack" BETWEEN 0 AND 31),
  "specialDefense" SMALLINT NOT NULL CHECK ("specialDefense" BETWEEN 0 AND 31),
  "speed" SMALLINT NOT NULL CHECK ("speed" BETWEEN 0 AND 31)
) INHERITS ("TableBase");

CREATE TABLE "EffortValue" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
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

-- TODO Add smogon tier generation connection -- separate join table with generation
-- TODO add unique indexes
CREATE TABLE "Pokemon" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "number" SMALLINT NOT NULL,
  "name" VARCHAR(32) NOT NULL,
  "form" VARCHAR(32),
  "isLegendary" BOOLEAN NOT NULL DEFAULT FALSE,
  "isMythical" BOOLEAN NOT NULL DEFAULT FALSE,
  "isMega" BOOLEAN NOT NULL DEFAULT FALSE,
  "prevolvedPokemonId" BIGINT REFERENCES "Pokemon" ("id"),
  UNIQUE ("name", "form")
) INHERITS ("TableBase");

CREATE TABLE "PokemonGeneration" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "pokemonId" BIGINT NOT NULL REFERENCES "Pokemon" ("id"),
  "generation" GENERATION NOT NULL,
  "mainType" POKETYPE NOT NULL,
  "subType" POKETYPE,
  "stage" SMALLINT NOT NULL DEFAULT 1,
  "baseStatTotal" SMALLINT NOT NULL CHECK("baseStatTotal" BETWEEN 0 AND 1530),
  "hpBaseStat" SMALLINT NOT NULL CHECK ("hpBaseStat" BETWEEN 0 AND 255),
  "attackBaseStat" SMALLINT NOT NULL CHECK ("attackBaseStat" BETWEEN 0 AND 255),
  "defenseBaseStat" SMALLINT NOT NULL CHECK ("defenseBaseStat" BETWEEN 0 AND 255),
  "specialAttackBaseStat" SMALLINT NOT NULL CHECK ("specialAttackBaseStat" BETWEEN 0 AND 255),
  "specialDefenseBaseStat" SMALLINT NOT NULL CHECK ("specialDefenseBaseStat" BETWEEN 0 AND 255),
  "speedBaseStat" SMALLINT NOT NULL CHECK ("speedBaseStat" BETWEEN 0 AND 255),
  "abilityId" BIGINT NOT NULL REFERENCES "Ability" ("id"),
  "alternateAbilityId" BIGINT REFERENCES "Ability" ("id"),
  "hiddenAbilityId" BIGINT REFERENCES "Ability" ("id"),
  "spriteUri" VARCHAR(256) NOT NULL
) INHERITS ("TableBase");

CREATE TABLE "PokemonTier" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "pokemonId" BIGINT NOT NULL REFERENCES "Pokemon" ("id"),
  "generation" GENERATION NOT NULL,
  "tier" TIER NOT NULL
) INHERITS ("TableBase");

CREATE TABLE "Item" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "name" VARCHAR(64) NOT NULL UNIQUE,
  "effect" VARCHAR(128) NOT NULL
) INHERITS ("TableBase");


CREATE TYPE DAMAGE AS ENUM(
  'PHYSICAL',
  'SPECIAL',
  'STATUS'
);

CREATE TABLE "Move" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "name" VARCHAR(32) NOT NULL UNIQUE,
  "type" POKETYPE NOT NULL,
  "damage" DAMAGE NOT NULL,
  "accuracy" SMALLINT CHECK ("accuracy" BETWEEN 0 AND 100),
  "pp" SMALLINT NOT NULL CHECK ("pp" BETWEEN 1 AND 56),
  "effect" VARCHAR(128)
) INHERITS ("TableBase");

CREATE TABLE "TrainerPokemon" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "name" VARCHAR(32),
  "pokemonGenerationId" BIGINT NOT NULL REFERENCES "PokemonGeneration" ("id"),
  "trainerId" BIGINT NOT NULL REFERENCES "Trainer" ("id"),
  "natureId" BIGINT NOT NULL REFERENCES "Nature" ("id"),
  "abilityId" BIGINT NOT NULL REFERENCES "Ability" ("id"),
  "individualValueId" BIGINT NOT NULL REFERENCES "IndividualValue" ("id"),
  "effortValueId" BIGINT NOT NULL REFERENCES "EffortValue" ("id"),
  "move1Id" BIGINT NOT NULL REFERENCES "Move" ("id"),
  "move2Id" BIGINT REFERENCES "Move" ("id"),
  "move3Id" BIGINT REFERENCES "Move" ("id"),
  "move4Id" BIGINT REFERENCES "Move" ("id"),
  "happiness" SMALLINT NOT NULL DEFAULT 255 CHECK ("happiness" BETWEEN 0 AND 255),
  "isShiny" BOOLEAN NOT NULL DEFAULT FALSE,
  "level" SMALLINT NOT NULL DEFAULT 100 CHECK ("level" BETWEEN 0 AND 100),
  "itemId" BIGINT NOT NULL REFERENCES "Item" ("id")
) INHERITS ("TableBase");

-- - Enforce limitation on amount of registered pokemon for each league
CREATE TABLE "LeaguePokemon" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "trainerPokemonId" BIGINT NOT NULL REFERENCES "TrainerPokemon" ("id"),
  "leagueId" BIGINT NOT NULL REFERENCES "League" ("id")
) INHERITS ("TableBase");
