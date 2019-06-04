package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.entity.TierTitle
import com.immanuelqrw.ikanobuntai.api.entity.Trainer
import com.immanuelqrw.ikanobuntai.api.entity.TrainerTitle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID
import com.immanuelqrw.ikanobuntai.api.service.unit.TrainerTitleService as UnitTrainerTitleService

@Service
class TrainerTitleService {

    @Autowired
    private lateinit var trainerTitleService: UnitTrainerTitleService

    fun findByTrainerId(trainerId: UUID): TrainerTitle? {
        val page = PageRequest.of(1, 1, Sort.by("rank"))

        return trainerTitleService.findAll(page, "trainerId:$trainerId").content.firstOrNull()
    }

    fun findTitleByTierTitleId(tierTitleId: UUID): TrainerTitle? {
        val page = PageRequest.of(1, 1)

        return trainerTitleService.findAll(page, "tierTitle:$tierTitleId;lostOn:").content.firstOrNull()
    }

    fun transferTitle(defender: Trainer, challenger: Trainer, tierTitle: TierTitle, foughtOn: LocalDateTime) {
        val defenderTitle: TrainerTitle = findTitleByTierTitleId(tierTitle.id!!)!!

        val lostDefenderTitle = defenderTitle.copy(lostOn = foughtOn)
        val wonChallengerTitle = defenderTitle.copy(trainer = challenger, wonOn = foughtOn)
        wonChallengerTitle.id = null

        trainerTitleService.replace(defenderTitle.id!!, lostDefenderTitle)
        trainerTitleService.create(wonChallengerTitle)
    }

}
