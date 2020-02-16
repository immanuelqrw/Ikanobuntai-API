package com.immanuelqrw.ikanobuntai.api.repository

import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.ikanobuntai.api.entity.LeaguePokemon
import org.springframework.stereotype.Repository

/**
 * Repository interface for [LeaguePokemon]
 */
@Repository
interface LeaguePokemonRepository : BaseUniqueRepository<LeaguePokemon>
