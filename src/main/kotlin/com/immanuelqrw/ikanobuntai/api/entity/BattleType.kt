package com.immanuelqrw.ikanobuntai.api.entity

enum class BattleType(val hasPrize: Boolean = false, val isGrunt: Boolean = false) {
    NON_RANKED,
    WILD,
    TITLE,
    GYM(false, true),
    GYM_LEADER(true, true),
    LEAGUE_FINALIST(false),
    ELITE_FOUR(false, true),
    LEAGUE_CHAMPION(true),
    FRONTIER(false, true),
    FRONTIER_BRAIN(true),
    WORLD_FINALIST(false, true),
    WORLD_CHAMPION(true)
}
