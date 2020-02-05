package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.Tier
import com.immanuelqrw.ikanobuntai.api.entity.Trainer
import com.immanuelqrw.ikanobuntai.api.entity.TrainerTitle
import com.immanuelqrw.ikanobuntai.api.repository.TrainerTitleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class TrainerTitleSeekService : BaseUniqueService<TrainerTitle>(TrainerTitle::class.java) {

    @Autowired
    private lateinit var trainerTitleRepository: TrainerTitleRepository

    fun findByTrainerIdTier(trainerId: UUID, tier: Tier): TrainerTitle? {
        return findAll("trainerId:$trainerId").firstOrNull { trainerTitle ->
            trainerTitle.tierTitle.tier == tier
        }
    }

    fun findTitleByTierTitleId(tierTitleId: UUID): TrainerTitle? {
        return findAll("tierTitle:$tierTitleId;lostOn:").firstOrNull()
    }

    fun transferTitle(challenger: Trainer, tierTitleId: UUID, foughtOn: LocalDateTime) {
        val defenderTitle: TrainerTitle = findTitleByTierTitleId(tierTitleId)!!

        val wonChallengerTitle = defenderTitle.copy(trainer = challenger, wonOn = foughtOn)
        wonChallengerTitle.id = null

        val lostDefenderChange: Map<String, LocalDateTime> = mapOf("toBeFoughtOn" to foughtOn)
        modify(defenderTitle.id!!, lostDefenderChange)
        create(wonChallengerTitle)
    }

}
