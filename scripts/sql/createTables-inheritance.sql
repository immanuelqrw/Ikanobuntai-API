
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

CREATE TABLE "Rank" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "name" VARCHAR(64) NOT NULL UNIQUE
) INHERITS ("TableBase");

CREATE TABLE "Tier" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "name" VARCHAR(64) NOT NULL UNIQUE
) INHERITS ("TableBase");

CREATE TABLE "Stage" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "name" VARCHAR(64) NOT NULL UNIQUE
) INHERITS ("TableBase");

CREATE TABLE "Title" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "name" VARCHAR(64) NOT NULL UNIQUE,
  "tierId" BIGINT NOT NULL REFERENCES "Tier" ("id")
) INHERITS ("TableBase");

CREATE TABLE "Configuration" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "name" VARCHAR(64) NOT NULL,
  "value" VARCHAR(64) NOT NULL,
  "type" VARCHAR(32) NOT NULL,
  "tierId" BIGINT NOT NULL REFERENCES "Tier" ("id"),
  CONSTRAINT "uq_name_tier" UNIQUE ("name", "tierId")
) INHERITS ("TableBase");

CREATE TABLE "Format" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "name" VARCHAR(64) NOT NULL UNIQUE,
  "description" VARCHAR(256) NOT NULL,
  "hasRuleset" BOOLEAN NOT NULL DEFAULT FALSE
) INHERITS ("TableBase");

CREATE TABLE "League" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "name" VARCHAR(64) NOT NULL UNIQUE,
  "stageId" BIGINT NOT NULL REFERENCES "Stage" ("id"),
  "tierId" BIGINT NOT NULL REFERENCES "Tier" ("id"),
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
  "tierId" BIGINT NOT NULL REFERENCES "Tier" ("id"),
  "rankId" BIGINT NOT NULL REFERENCES "Rank" ("id"),
  "rating" SMALLINT NOT NULL DEFAULT 1000 CHECK ("rating" >= 1000)
) INHERITS ("TableBase");

CREATE TABLE "TrainerTitle" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "trainerId" BIGINT NOT NULL REFERENCES "Trainer" ("id"),
  "titleId" BIGINT NOT NULL REFERENCES "Title" ("id"),
  "wonOn" TIMESTAMP NOT NULL,
  "lostOn" TIMESTAMP
) INHERITS ("TableBase");

CREATE TABLE "BattleRating" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "defenderTitleId" BIGINT NOT NULL REFERENCES "Title" ("id"),
  "challengerTitleId" BIGINT NOT NULL REFERENCES "Title" ("id"),
  "leagueId" BIGINT NOT NULL REFERENCES "League" ("id"),
  "value" SMALLINT NOT NULL,
  CHECK ("defenderTitleId" <> "challengerTitleId")
) INHERITS ("TableBase");

CREATE TYPE RESULT AS ENUM (
    'WIN',
    'DRAW',
    'LOSS'
);

CREATE TABLE "Battle" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "defenderId" BIGINT NOT NULL REFERENCES "Trainer" ("id"),
  "challengerId" BIGINT NOT NULL REFERENCES "Trainer" ("id"),
  "winnerId" BIGINT REFERENCES "Trainer" ("id"),
  "rankId" BIGINT NOT NULL REFERENCES "Rank" ("id"),
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

CREATE TABLE "Generation" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "number" SMALLINT NOT NULL,
  "region" VARCHAR(8) NOT NULL
) INHERITS ("TableBase");

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
  "generationId" BIGINT NOT NULL REFERENCES "Generation" ("id"),
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
  "generationId" BIGINT NOT NULL REFERENCES "Generation" ("id"),
  "tierId" BIGINT NOT NULL REFERENCES "Tier" ("id")
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

-- Team

