package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.PrizeGrunt
import com.immanuelqrw.ikanobuntai.api.entity.Tier
import com.immanuelqrw.ikanobuntai.api.entity.Title
import com.immanuelqrw.ikanobuntai.api.repository.PrizeGruntRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class PrizeGruntSeekService : BaseUniqueService<PrizeGrunt>(PrizeGrunt::class.java) {

    @Autowired
    private lateinit var prizeGruntRepository: PrizeGruntRepository

    fun findByLeagueAndTrainer(leagueName: String, trainerName: String): PrizeGrunt {
        return prizeGruntRepository.findByLeagueNameAndTrainerNameAndRemovedOnIsNull(leagueName, trainerName)
            ?: throw EntityNotFoundException()
    }

    fun findAllByLeagueAndTierAndTitle(leagueName: String, tier: Tier, title: Title): List<PrizeGrunt> {
        return prizeGruntRepository.findAllByLeagueNameAndTierTitleTierAndTierTitleTitleAndRemovedOnIsNull(leagueName, tier, title)
    }

}
