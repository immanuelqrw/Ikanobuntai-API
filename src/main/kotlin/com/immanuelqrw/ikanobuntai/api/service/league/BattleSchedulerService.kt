package com.immanuelqrw.ikanobuntai.api.service.league

import com.immanuelqrw.ikanobuntai.api.dto.BattleVerification
import com.immanuelqrw.ikanobuntai.api.dto.PlannedBattle
import com.immanuelqrw.ikanobuntai.api.entity.ScheduledBattle
import com.immanuelqrw.ikanobuntai.api.service.battle.BattleVerificationService
import com.immanuelqrw.ikanobuntai.api.service.search.LeagueService
import com.immanuelqrw.ikanobuntai.api.service.search.LeagueTrainerService
import com.immanuelqrw.ikanobuntai.api.service.search.ScheduledBattleService
import com.immanuelqrw.ikanobuntai.api.service.search.TrainerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BattleSchedulerService {

    @Autowired
    private lateinit var scheduledBattleService: ScheduledBattleService

    @Autowired
    private lateinit var trainerService: TrainerService

    @Autowired
    private lateinit var leagueService: LeagueService

    @Autowired
    private lateinit var leagueTrainerService: LeagueTrainerService

    @Autowired
    private lateinit var battleVerificationService: BattleVerificationService

    fun schedule(plannedBattle: PlannedBattle): ScheduledBattle {
        val league = leagueService.findByName(plannedBattle.league)!!

        val defenderId = trainerService.findByName(plannedBattle.defender)!!.id!!
        val defender = leagueTrainerService.findTrainerByLeagueTrainer(league.id!!, defenderId)!!

        val challengerId = trainerService.findByName(plannedBattle.challenger)!!.id!!
        val challenger = leagueTrainerService.findTrainerByLeagueTrainer(league.id!!, challengerId)!!

        val battleVerification = BattleVerification(
            defenderId = defenderId,
            challengerId = challengerId,
            battleType = plannedBattle.type,
            // ! Create a generic WILD perpetual league and make non-nullable for WILD battles of each tier
            leagueId = league.id!!,
            tierTitle = plannedBattle.defendingTierTitle
        )

        val scheduledBattle = ScheduledBattle(
            type = plannedBattle.type,
            defender = defender,
            challenger = challenger,
            league = league,
            toBeFoughtOn = plannedBattle.toBeFoughtOn
        )

        // Validate
        battleVerificationService.acquireType(battleVerification)

        return scheduledBattleService.create(scheduledBattle)
    }

}
