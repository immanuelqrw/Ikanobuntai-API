package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.Tier
import com.immanuelqrw.ikanobuntai.api.entity.TrainerRating
import com.immanuelqrw.ikanobuntai.api.repository.TrainerRatingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TrainerRatingSeekService : BaseUniqueService<TrainerRating>(TrainerRating::class.java) {

    @Autowired
    private lateinit var trainerRatingRepository: TrainerRatingRepository

    fun findByTrainerTier(trainerId: UUID, tier: Tier): TrainerRating? {
        return findAll("trainerId:$trainerId;tier:$tier").firstOrNull()
    }

}
