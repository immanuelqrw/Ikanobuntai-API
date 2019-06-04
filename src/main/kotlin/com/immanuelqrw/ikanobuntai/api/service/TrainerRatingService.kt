package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.entity.Tier
import com.immanuelqrw.ikanobuntai.api.entity.Trainer
import com.immanuelqrw.ikanobuntai.api.entity.TrainerRating
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.TrainerRatingService as UnitTrainerRatingService

@Service
class TrainerRatingService {

    @Autowired
    private lateinit var trainerRatingService: UnitTrainerRatingService

    fun findByTrainerTier(trainer: Trainer, tier: Tier): TrainerRating? {
        val page = PageRequest.of(1, 1)

        return trainerRatingService.findAll(page, "trainerId:${trainer.id};tier:$tier").content.firstOrNull()
    }

}
