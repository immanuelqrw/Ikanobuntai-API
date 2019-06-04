package com.immanuelqrw.ikanobuntai.api.entity

enum class BattleType(val hasPrize: Boolean) {
    NON_RANKED(false),
    WILD(false),
    TITLE(false),
    GYM(false),
    GYM_LEADER(true),
    LEAGUE_FINALIST(false),
    ELITE_FOUR(false),
    LEAGUE_CHAMPION(true),
    FRONTIER(false),
    FRONTIER_BRAIN(true),
    WORLD_FINALIST(false),
    WORLD_CHAMPION(true)
}
