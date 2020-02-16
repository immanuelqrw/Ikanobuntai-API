package com.immanuelqrw.ikanobuntai.api.repository

import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.ikanobuntai.api.entity.Tier
import com.immanuelqrw.ikanobuntai.api.entity.TrainerRating
import org.springframework.stereotype.Repository

/**
 * Repository interface for [TrainerRating]
 */
@Repository
interface TrainerRatingRepository : BaseUniqueRepository<TrainerRating> {

    fun findByTrainerNameAndTierAndRemovedOnIsNull(trainerName: String, tier: Tier): TrainerRating?

}
