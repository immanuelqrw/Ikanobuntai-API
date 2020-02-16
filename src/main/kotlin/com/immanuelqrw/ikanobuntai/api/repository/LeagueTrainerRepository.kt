package com.immanuelqrw.ikanobuntai.api.repository

import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.ikanobuntai.api.entity.LeagueTrainer
import org.springframework.stereotype.Repository

/**
 * Repository interface for [LeagueTrainer]
 */
@Repository
interface LeagueTrainerRepository : BaseUniqueRepository<LeagueTrainer> {

    fun findAllByLeagueNameAndRemovedOnIsNull(leagueName: String): List<LeagueTrainer>

    fun findAllByTrainerNameAndRemovedOnIsNull(trainerName: String): List<LeagueTrainer>

    fun findByLeagueNameAndTrainerNameAndRemovedOnIsNull(leagueName: String, trainerName: String): LeagueTrainer?

}
