package com.immanuelqrw.ikanobuntai.api.repository

import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.ikanobuntai.api.entity.Move
import org.springframework.stereotype.Repository

/**
 * Repository interface for [Move]
 */
@Repository
interface MoveRepository : BaseUniqueRepository<Move> {

    fun findByNameAndRemovedOnIsNull(name: String): Move?

}
