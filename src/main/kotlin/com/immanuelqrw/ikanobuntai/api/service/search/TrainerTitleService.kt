package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.ikanobuntai.api.entity.Tier
import com.immanuelqrw.ikanobuntai.api.entity.Trainer
import com.immanuelqrw.ikanobuntai.api.entity.TrainerTitle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID
import com.immanuelqrw.ikanobuntai.api.service.unit.TrainerTitleService as UnitTrainerTitleService

@Service
class TrainerTitleService {

    @Autowired
    private lateinit var trainerTitleService: UnitTrainerTitleService

    fun findByTrainerIdTier(trainerId: UUID, tier: Tier): TrainerTitle? {
        return trainerTitleService.findAll("trainerId:$trainerId").firstOrNull { trainerTitle ->
            trainerTitle.tierTitle.tier == tier
        }
    }

    fun findTitleByTierTitleId(tierTitleId: UUID): TrainerTitle? {
        return trainerTitleService.findAll("tierTitle:$tierTitleId;lostOn:").firstOrNull()
    }

    fun transferTitle(challenger: Trainer, tierTitleId: UUID, foughtOn: LocalDateTime) {
        val defenderTitle: TrainerTitle = findTitleByTierTitleId(tierTitleId)!!

        val wonChallengerTitle = defenderTitle.copy(trainer = challenger, wonOn = foughtOn)
        wonChallengerTitle.id = null

        val lostDefenderChange: Map<String, LocalDateTime> = mapOf("foughtOn" to foughtOn)
        trainerTitleService.modify(defenderTitle.id!!, lostDefenderChange)
        trainerTitleService.create(wonChallengerTitle)
    }

}
