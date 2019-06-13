package com.immanuelqrw.ikanobuntai.api.service.battle

import com.immanuelqrw.ikanobuntai.api.dto.BattleVerification
import com.immanuelqrw.ikanobuntai.api.dto.PlannedBattle
import com.immanuelqrw.ikanobuntai.api.entity.ScheduledBattle
import com.immanuelqrw.ikanobuntai.api.service.search.LeagueService
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
    private lateinit var battleVerificationService: BattleVerificationService

    fun schedule(plannedBattle: PlannedBattle): ScheduledBattle {
        val defender = trainerService.findByName(plannedBattle.defender)!!
        val challenger = trainerService.findByName(plannedBattle.challenger)!!

        val league = plannedBattle.league?.let { league ->
            leagueService.findByName(league)
        }

        val battleVerification = BattleVerification(
            defenderId = defender.id!!,
            challengerId = challenger.id!!,
            battleType = plannedBattle.type,
            leagueId = league?.id,
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
