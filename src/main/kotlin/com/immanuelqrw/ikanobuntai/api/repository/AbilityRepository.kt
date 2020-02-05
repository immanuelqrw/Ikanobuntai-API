package com.immanuelqrw.ikanobuntai.api.repository

import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.ikanobuntai.api.entity.Ability

/**
 * Repository interface for [Ability]
 */
interface AbilityRepository : BaseUniqueRepository<Ability> {

    fun findByNameAndRemovedOnIsNull(name: String): Ability?

}
