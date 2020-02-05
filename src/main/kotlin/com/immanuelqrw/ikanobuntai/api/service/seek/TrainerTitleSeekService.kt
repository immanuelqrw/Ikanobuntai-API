package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.Tier
import com.immanuelqrw.ikanobuntai.api.entity.Title
import com.immanuelqrw.ikanobuntai.api.entity.Trainer
import com.immanuelqrw.ikanobuntai.api.entity.TrainerTitle
import com.immanuelqrw.ikanobuntai.api.repository.TrainerTitleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.persistence.EntityNotFoundException

@Service
class TrainerTitleSeekService : BaseUniqueService<TrainerTitle>(TrainerTitle::class.java) {

    @Autowired
    private lateinit var trainerTitleRepository: TrainerTitleRepository

    fun findByTrainerAndTier(trainerName: String, tier: Tier): TrainerTitle {
        return trainerTitleRepository.findByTrainerNameAndTierTitleTierAndRemovedOnIsNull(trainerName, tier)
            ?: throw EntityNotFoundException()
    }

    fun findByTierAndTitle(tier: Tier, title: Title): TrainerTitle {
        return trainerTitleRepository.findByTierTitleTierAndTierTitleTitleAndRemovedOnIsNull(tier, title)
            ?: throw EntityNotFoundException()
    }

    fun transferTitle(challenger: Trainer, tier: Tier, title: Title, foughtOn: LocalDateTime) {
        val defenderTitle: TrainerTitle = findByTierAndTitle(tier, title)

        val wonChallengerTitle = defenderTitle.copy(trainer = challenger, wonOn = foughtOn)

        val lostDefenderChange: Map<String, LocalDateTime> = mapOf("toBeFoughtOn" to foughtOn)
        // ! Fix patch
        modify(defenderTitle.id, lostDefenderChange)
        create(wonChallengerTitle)
    }

}
