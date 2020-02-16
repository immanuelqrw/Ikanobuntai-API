package com.immanuelqrw.ikanobuntai.api.repository

import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.ikanobuntai.api.entity.Ability
import org.springframework.stereotype.Repository

/**
 * Repository interface for [Ability]
 */
@Repository
interface AbilityRepository : BaseUniqueRepository<Ability> {

    fun findByNameAndRemovedOnIsNull(name: String): Ability?

}
