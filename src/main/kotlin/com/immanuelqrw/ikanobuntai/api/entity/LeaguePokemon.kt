package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity

data class LeaguePokemon(
    val trainerPokemon: TrainerPokemon,
    val league: League
) : BaseUniqueEntity()
