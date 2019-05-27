package com.immanuelqrw.ikanobuntai.api.dto

import com.immanuelqrw.ikanobuntai.api.entity.Trainer
import com.immanuelqrw.ikanobuntai.api.entity.TrainerPokemon

data class PokemonTeam(
    val trainer: Trainer,
    val trainerPokemon: Collection<TrainerPokemon>
)
