package com.immanuelqrw.ikanobuntai.api.entity

// ? Move minElo to configuration file
enum class Rank(val level: Int, val minElo: Int) {
    JUNIOR_TRAINER(1, 1000),
    TRAINER(2, 1100),
    ACE_TRAINER(3, 1200),
    LEAGUE_CHALLENGER(4, 1300),
    LEAGUE_FINALIST(5, 1400),
    ASCENDANT(6, 1600),
    WORLD_CHALLENGER(7, 1800),
    WORLD_RANKER(8, 2000),
    MASTER(9, 2200)
}
