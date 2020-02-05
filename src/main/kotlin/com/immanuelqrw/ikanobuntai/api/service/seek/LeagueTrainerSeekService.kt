package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.League
import com.immanuelqrw.ikanobuntai.api.entity.LeagueTrainer
import com.immanuelqrw.ikanobuntai.api.entity.Trainer
import com.immanuelqrw.ikanobuntai.api.repository.LeagueTrainerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class LeagueTrainerSeekService : BaseUniqueService<LeagueTrainer>(LeagueTrainer::class.java) {

    @Autowired
    private lateinit var leagueTrainerRepository: LeagueTrainerRepository

    fun findAllTrainersByLeague(leagueId: UUID): List<Trainer> {
        return findAll("leagueId:$leagueId").map { leagueTrainer ->
            leagueTrainer.trainer
        }
    }

    fun findTrainerByLeagueTrainer(leagueId: UUID, trainerId: UUID): Trainer? {
        return findAllTrainersByLeague(leagueId).firstOrNull { trainer ->
            trainer.id == trainerId
        }
    }

    fun findAllLeaguesByTrainer(trainerId: UUID): List<League> {
        return findAll("trainerId:$trainerId").map { leagueTrainer ->
            leagueTrainer.league
        }
    }

}
