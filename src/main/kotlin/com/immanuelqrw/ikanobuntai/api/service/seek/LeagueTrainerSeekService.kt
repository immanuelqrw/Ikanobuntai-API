package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.League
import com.immanuelqrw.ikanobuntai.api.entity.LeagueTrainer
import com.immanuelqrw.ikanobuntai.api.entity.Trainer
import com.immanuelqrw.ikanobuntai.api.repository.LeagueTrainerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class LeagueTrainerSeekService : BaseUniqueService<LeagueTrainer>(LeagueTrainer::class.java) {

    @Autowired
    private lateinit var leagueTrainerRepository: LeagueTrainerRepository

    fun findAllTrainersByLeague(leagueName: String): List<Trainer> {
        return leagueTrainerRepository.findAllByLeagueNameAndRemovedOnIsNull(leagueName).map { leagueTrainer ->
            leagueTrainer.trainer
        }
    }

    fun findAllLeaguesByTrainer(trainerName: String): List<League> {
        return leagueTrainerRepository.findAllByTrainerNameAndRemovedOnIsNull(trainerName).map { leagueTrainer ->
            leagueTrainer.league
        }
    }

    fun findTrainerByLeagueAndTrainer(leagueName: String, trainerName: String): Trainer {
        return leagueTrainerRepository.findByLeagueNameAndTrainerNameAndRemovedOnIsNull(leagueName, trainerName)?.trainer
            ?: throw EntityNotFoundException()
    }

}
