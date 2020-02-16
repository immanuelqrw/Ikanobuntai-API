package com.immanuelqrw.ikanobuntai.api.repository

import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.ikanobuntai.api.entity.PrizeGrunt
import com.immanuelqrw.ikanobuntai.api.entity.Tier
import com.immanuelqrw.ikanobuntai.api.entity.Title
import org.springframework.stereotype.Repository

/**
 * Repository interface for [PrizeGrunt]
 */
@Repository
interface PrizeGruntRepository : BaseUniqueRepository<PrizeGrunt> {

    fun findByLeagueNameAndTrainerNameAndRemovedOnIsNull(leagueName: String, trainerName: String): PrizeGrunt?

    fun findAllByLeagueNameAndTierTitleTierAndTierTitleTitleAndRemovedOnIsNull(leagueName: String, tier: Tier, title: Title): List<PrizeGrunt>

}
