package com.immanuelqrw.ikanobuntai.api.repository

import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.ikanobuntai.api.entity.PokemonGeneration
import org.springframework.stereotype.Repository

/**
 * Repository interface for [PokemonGeneration]
 */
@Repository
interface PokemonGenerationRepository : BaseUniqueRepository<PokemonGeneration>
