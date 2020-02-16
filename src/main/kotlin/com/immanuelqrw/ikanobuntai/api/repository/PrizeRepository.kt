package com.immanuelqrw.ikanobuntai.api.repository

import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.ikanobuntai.api.entity.Prize
import org.springframework.stereotype.Repository

/**
 * Repository interface for [Prize]
 */
@Repository
interface PrizeRepository : BaseUniqueRepository<Prize> {

    fun findByNameAndRemovedOnIsNull(name: String): Prize?

}
