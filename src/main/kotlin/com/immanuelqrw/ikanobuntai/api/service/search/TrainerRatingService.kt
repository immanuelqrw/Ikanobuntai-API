package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.Tier
import com.immanuelqrw.ikanobuntai.api.entity.TrainerRating
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TrainerRatingService : BaseUniqueService<TrainerRating>() {

    fun findByTrainerTier(trainerId: UUID, tier: Tier): TrainerRating? {
        return findAll("trainerId:$trainerId;tier:$tier").firstOrNull()
    }

}
