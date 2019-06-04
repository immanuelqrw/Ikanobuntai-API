package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.UNIQUE_PAGE_REQUEST
import com.immanuelqrw.ikanobuntai.api.entity.Tier
import com.immanuelqrw.ikanobuntai.api.entity.Trainer
import com.immanuelqrw.ikanobuntai.api.entity.TrainerRating
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.TrainerRatingService as UnitTrainerRatingService

@Service
class TrainerRatingService {

    @Autowired
    private lateinit var trainerRatingService: UnitTrainerRatingService

    fun findByTrainerTier(trainer: Trainer, tier: Tier): TrainerRating? {
        return trainerRatingService.findAll(UNIQUE_PAGE_REQUEST, "trainerId:${trainer.id};tier:$tier").content.firstOrNull()
    }

}
