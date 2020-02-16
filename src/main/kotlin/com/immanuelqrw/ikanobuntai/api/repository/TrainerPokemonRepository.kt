package com.immanuelqrw.ikanobuntai.api.repository

import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.ikanobuntai.api.entity.TrainerPokemon
import org.springframework.stereotype.Repository

/**
 * Repository interface for [TrainerPokemon]
 */
@Repository
interface TrainerPokemonRepository : BaseUniqueRepository<TrainerPokemon>
