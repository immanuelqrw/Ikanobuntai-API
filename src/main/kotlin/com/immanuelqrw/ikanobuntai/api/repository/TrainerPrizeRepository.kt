package com.immanuelqrw.ikanobuntai.api.repository

import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.ikanobuntai.api.entity.TrainerPrize
import org.springframework.stereotype.Repository

/**
 * Repository interface for [TrainerPrize]
 */
@Repository
interface TrainerPrizeRepository : BaseUniqueRepository<TrainerPrize>
