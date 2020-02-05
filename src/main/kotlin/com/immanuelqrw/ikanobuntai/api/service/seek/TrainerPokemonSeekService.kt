package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.TrainerPokemon
import org.springframework.stereotype.Service

@Service
class TrainerPokemonSeekService : BaseUniqueService<TrainerPokemon>(TrainerPokemon::class.java)
