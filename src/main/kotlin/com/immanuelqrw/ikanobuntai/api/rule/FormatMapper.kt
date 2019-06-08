package com.immanuelqrw.ikanobuntai.api.rule

object FormatMapper {

    private val formats: Collection<BattleFormat> = listOf(
        BannedItemFormat,
        BannedPokemonFormat,
        LevelLimitFormat,
        MonotypeFormat,
        SharedMonotypeFormat
    )

    fun map(name: String): BattleFormat {
        return formats.first { format ->
            format.name == name
        }
    }
}
