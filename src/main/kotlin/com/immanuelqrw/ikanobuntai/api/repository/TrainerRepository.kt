package com.immanuelqrw.ikanobuntai.api.repository

import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.ikanobuntai.api.entity.Trainer
import org.springframework.stereotype.Repository

/**
 * Repository interface for [Trainer]
 */
@Repository
interface TrainerRepository : BaseUniqueRepository<Trainer> {

    fun findByNameAndRemovedOnIsNull(name: String): Trainer?

}
