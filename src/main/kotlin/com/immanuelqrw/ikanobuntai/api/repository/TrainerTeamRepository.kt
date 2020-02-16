package com.immanuelqrw.ikanobuntai.api.repository

import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.ikanobuntai.api.entity.TrainerTeam
import org.springframework.stereotype.Repository

/**
 * Repository interface for [TrainerTeam]
 */
@Repository
interface TrainerTeamRepository : BaseUniqueRepository<TrainerTeam> {

    fun findByNameAndRemovedOnIsNull(name: String): TrainerTeam?

}
