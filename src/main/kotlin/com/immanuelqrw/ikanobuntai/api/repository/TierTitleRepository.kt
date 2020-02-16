package com.immanuelqrw.ikanobuntai.api.repository

import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.ikanobuntai.api.entity.TierTitle
import org.springframework.stereotype.Repository

/**
 * Repository interface for [TierTitle]
 */
@Repository
interface TierTitleRepository : BaseUniqueRepository<TierTitle>
