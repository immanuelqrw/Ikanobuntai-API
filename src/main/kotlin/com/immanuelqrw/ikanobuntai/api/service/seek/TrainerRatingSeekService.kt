package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.Tier
import com.immanuelqrw.ikanobuntai.api.entity.TrainerRating
import com.immanuelqrw.ikanobuntai.api.repository.TrainerRatingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class TrainerRatingSeekService : BaseUniqueService<TrainerRating>(TrainerRating::class.java) {

    @Autowired
    private lateinit var trainerRatingRepository: TrainerRatingRepository

    fun findByTrainerAndTier(trainerName: String, tier: Tier): TrainerRating {
        return trainerRatingRepository.findByTrainerNameAndTierAndRemovedOnIsNull(trainerName, tier)
            ?: throw EntityNotFoundException()
    }

}
