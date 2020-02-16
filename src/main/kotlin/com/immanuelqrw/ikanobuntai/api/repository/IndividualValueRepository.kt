package com.immanuelqrw.ikanobuntai.api.repository

import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.ikanobuntai.api.entity.IndividualValue
import org.springframework.stereotype.Repository

/**
 * Repository interface for [IndividualValue]
 */
@Repository
interface IndividualValueRepository : BaseUniqueRepository<IndividualValue>
