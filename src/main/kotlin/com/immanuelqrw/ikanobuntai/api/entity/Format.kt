package com.immanuelqrw.ikanobuntai.api.entity

enum class Format(description: String) {
    BANNED_ITEM("Some items are banned"),
    BANNED_POKEMON("Some Pokemon are banned"),
    LEVEL_LIMIT("Pokemon over declared level not allowed"),
    MONOTYPE("Every Pokemon in this format might have the same type, across all teams"),
    SHARED_MONOTYPE("All Pokemon on a team must share at least one type"),
    LEVEL_RANGE("Minimum and Maximum levels allowed"),
    NO_MEGA("No Megas"),
    NO_LEGENDARY("No Legendaries"),
    NO_MYTHICAL("No Mythicals")
}
