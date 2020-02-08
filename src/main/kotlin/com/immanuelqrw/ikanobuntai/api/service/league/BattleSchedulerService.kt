package com.immanuelqrw.ikanobuntai.api.service.league

import com.immanuelqrw.ikanobuntai.api.dto.BattleVerification
import com.immanuelqrw.ikanobuntai.api.dto.PlannedBattle
import com.immanuelqrw.ikanobuntai.api.entity.ScheduledBattle
import com.immanuelqrw.ikanobuntai.api.service.battle.BattleVerificationService
import com.immanuelqrw.ikanobuntai.api.service.seek.LeagueSeekService
import com.immanuelqrw.ikanobuntai.api.service.seek.LeagueTrainerSeekService
import com.immanuelqrw.ikanobuntai.api.service.seek.ScheduledBattleSeekService
import com.immanuelqrw.ikanobuntai.api.service.seek.TrainerSeekService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BattleSchedulerService {

    @Autowired
    private lateinit var scheduledBattleSeekService: ScheduledBattleSeekService

    @Autowired
    private lateinit var trainerSeekService: TrainerSeekService

    @Autowired
    private lateinit var leagueSeekService: LeagueSeekService

    @Autowired
    private lateinit var leagueTrainerSeekService: LeagueTrainerSeekService

    @Autowired
    private lateinit var battleVerificationService: BattleVerificationService

    fun schedule(plannedBattle: PlannedBattle): ScheduledBattle {
        val league = leagueSeekService.findByName(plannedBattle.league)

        val defenderName = trainerSeekService.findByName(plannedBattle.defender).name
        val defender = leagueTrainerSeekService.findTrainerByLeagueAndTrainer(league.name, defenderName)

        val challengerName = trainerSeekService.findByName(plannedBattle.challenger).name
        val challenger = leagueTrainerSeekService.findTrainerByLeagueAndTrainer(league.name, challengerName)

        val battleVerification = BattleVerification(
            defenderName = defenderName,
            challengerName = challengerName,
            battleType = plannedBattle.type,
            // ! Create a generic WILD perpetual league and make non-nullable for WILD battles of each tier
            leagueName = league.name,
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

        return scheduledBattleSeekService.create(scheduledBattle)
    }

}
