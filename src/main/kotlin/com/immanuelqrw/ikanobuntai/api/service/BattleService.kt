package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.service.EloCalculationService
import com.immanuelqrw.ikanobuntai.api.entity.Battle
import com.immanuelqrw.ikanobuntai.api.service.unit.BattleService
import com.immanuelqrw.ikanobuntai.api.service.unit.TrainerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BattleService {

    @Autowired
    private lateinit var battleService: BattleService

    @Autowired
    private lateinit var trainerService: TrainerService

    @Autowired
    private lateinit var eloCalculationService: EloCalculationService

    fun create(battle: Battle): Battle {
        val (defenderChange, challengerChange) = eloCalculationService.calculateBattle(battle)

        val createdBattle: Battle = battleService.create(battle)

        val defenderEloChange: Map<String, Int> = mapOf("rating" to defenderChange)
        trainerService.modify(battle.defender.id!!, defenderEloChange)

        val challengerEloChange: Map<String, Int> = mapOf("rating" to challengerChange)
        trainerService.modify(battle.challenger.id!!, challengerEloChange)

        return createdBattle
    }

}
