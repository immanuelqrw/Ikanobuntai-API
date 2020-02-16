package com.immanuelqrw.ikanobuntai.api.repository

import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.ikanobuntai.api.entity.EffortValue
import org.springframework.stereotype.Repository

/**
 * Repository interface for [EffortValue]
 */
@Repository
interface EffortValueRepository : BaseUniqueRepository<EffortValue>
