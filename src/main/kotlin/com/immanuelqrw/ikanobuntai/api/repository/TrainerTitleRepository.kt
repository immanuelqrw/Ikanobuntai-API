package com.immanuelqrw.ikanobuntai.api.repository

import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.ikanobuntai.api.entity.Tier
import com.immanuelqrw.ikanobuntai.api.entity.Title
import com.immanuelqrw.ikanobuntai.api.entity.TrainerTitle
import org.springframework.stereotype.Repository

/**
 * Repository interface for [TrainerTitle]
 */
@Repository
interface TrainerTitleRepository : BaseUniqueRepository<TrainerTitle> {

    fun findByTrainerNameAndTierTitleTierAndRemovedOnIsNull(trainerName: String, tier: Tier): TrainerTitle?

    fun findByTierTitleTierAndTierTitleTitleAndRemovedOnIsNull(tier: Tier, title: Title): TrainerTitle?

}
