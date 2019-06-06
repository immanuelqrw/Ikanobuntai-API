package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.ikanobuntai.api.UNIQUE_PAGE_REQUEST
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
        val page = PageRequest.of(0, 1, Sort.by("rank"))

        return trainerTitleService.findAll(page, "trainerId:$trainerId").content.firstOrNull()
    }

    fun findTitleByTierTitleId(tierTitleId: UUID): TrainerTitle? {
        return trainerTitleService.findAll(UNIQUE_PAGE_REQUEST, "tierTitle:$tierTitleId;lostOn:").content.firstOrNull()
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
