package com.immanuelqrw.ikanobuntai.api.repository

import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.ikanobuntai.api.entity.PokemonTier
import org.springframework.stereotype.Repository

/**
 * Repository interface for [PokemonTier]
 */
@Repository
interface PokemonTierRepository : BaseUniqueRepository<PokemonTier>
