-- CREATE TEMP TABLE
CREATE TEMPORARY TABLE IF NOT EXISTS
  "PokemonStat" (
    "number" SMALLINT NOT NULL,
    "name" VARCHAR(32) NOT NULL,
    "form" VARCHAR(32),
    "mainType" POKETYPE NOT NULL,
    "subType" POKETYPE,
    "totalBaseStat" SMALLINT NOT NULL CHECK("totalBaseStat" BETWEEN 0 AND 1530),
    "hpBaseStat" SMALLINT NOT NULL CHECK ("hpBaseStat" BETWEEN 0 AND 255),
    "attackBaseStat" SMALLINT NOT NULL CHECK ("attackBaseStat" BETWEEN 0 AND 255),
    "defenseBaseStat" SMALLINT NOT NULL CHECK ("defenseBaseStat" BETWEEN 0 AND 255),
    "specialAttackBaseStat" SMALLINT NOT NULL CHECK ("specialAttackBaseStat" BETWEEN 0 AND 255),
    "specialDefenseBaseStat" SMALLINT NOT NULL CHECK ("specialDefenseBaseStat" BETWEEN 0 AND 255),
    "speedBaseStat" SMALLINT NOT NULL CHECK ("speedBaseStat" BETWEEN 0 AND 255),
    "imageUrl" VARCHAR(256) NOT NULL
  );

-- LOAD TEMP TABLE
COPY "PokemonStat" (
  "number",
  "name",
  "form",
  "mainType",
  "subType",
  "totalBaseStat",
  "hpBaseStat",
  "attackBaseStat",
  "defenseBaseStat",
  "specialAttackBaseStat",
  "specialDefenseBaseStat",
  "speedBaseStat",
  "imageUrl"
)
FROM 'ROOT_DIR:/configuration/data/pokemon-stats.psv'
DELIMITER '|'
CSV HEADER;

-- LOAD MAIN TABLES
INSERT INTO
  "Pokemon"(
    "number",
    "name",
    "form",
    "isLegendary",
    "isMythical",
    "isMega"
)
SELECT
  "number",
  "name",
  "form",
  NULL,
  NULL,
  NULL
FROM
  "PokemonStat";
