package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.ikanobuntai.api.entity.Tier
import com.immanuelqrw.ikanobuntai.api.entity.TrainerRating
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID
import com.immanuelqrw.ikanobuntai.api.service.unit.TrainerRatingService as UnitTrainerRatingService

@Service
class TrainerRatingService {

    @Autowired
    private lateinit var trainerRatingService: UnitTrainerRatingService

    fun findByTrainerTier(trainerId: UUID, tier: Tier): TrainerRating? {
        return trainerRatingService.findAll("trainerId:$trainerId;tier:$tier").firstOrNull()
    }

}
