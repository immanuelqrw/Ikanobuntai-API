package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.TrainerTeam
import com.immanuelqrw.ikanobuntai.api.repository.TrainerTeamRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class TrainerTeamSeekService : BaseUniqueService<TrainerTeam>(TrainerTeam::class.java) {

    @Autowired
    private lateinit var trainerTeamRepository: TrainerTeamRepository

    fun findByName(name: String): TrainerTeam {
        return trainerTeamRepository.findByNameAndRemovedOnIsNull(name) ?: throw EntityNotFoundException()
    }

}
