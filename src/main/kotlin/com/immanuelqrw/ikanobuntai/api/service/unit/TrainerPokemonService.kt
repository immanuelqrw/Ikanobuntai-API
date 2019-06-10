package com.immanuelqrw.ikanobuntai.api.service.unit

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.TrainerPokemon
import org.springframework.stereotype.Service

@Service("UnitTrainerPokemonService")
class TrainerPokemonService : BaseUniqueService<TrainerPokemon>()
