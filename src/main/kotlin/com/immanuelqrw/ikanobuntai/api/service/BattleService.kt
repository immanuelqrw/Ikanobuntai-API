package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.dto.PokemonBattle
import com.immanuelqrw.ikanobuntai.api.entity.Battle
import com.immanuelqrw.ikanobuntai.api.entity.Rank
import com.immanuelqrw.ikanobuntai.api.entity.Trainer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.BattleService as UnitBattleService
import com.immanuelqrw.ikanobuntai.api.service.unit.TrainerService as UnitTrainerService

@Service
class BattleService {

    @Autowired
    private lateinit var battleService: UnitBattleService

    @Autowired
    private lateinit var trainerService: TrainerService

    @Autowired
    private lateinit var unitTrainerService: UnitTrainerService

    @Autowired
    private lateinit var rankService: RankService

    @Autowired
    private lateinit var eloCalculationService: EloCalculationService

    fun create(pokemonBattle: PokemonBattle): Battle {
        val defender = trainerService.findByName(pokemonBattle.defender)
        val challenger = trainerService.findByName(pokemonBattle.challenger)
        val winner: Trainer? = when(pokemonBattle.winner) {
            pokemonBattle.defender -> defender
            pokemonBattle.challenger -> challenger
            else -> null
        }
        val rank: Rank? = rankService.findByName(pokemonBattle.rank)

        val battle = Battle(
            defender = defender!!,
            challenger = challenger!!,
            winner = winner,
            rank = rank!!,
            value = pokemonBattle.value,
            foughtOn = pokemonBattle.foughtOn
        )

        val (defenderChange, challengerChange) = eloCalculationService.calculateBattle(battle)

        val createdBattle: Battle = battleService.create(battle)

        val defenderEloChange: Map<String, Int> = mapOf("rating" to defenderChange)
        unitTrainerService.modify(battle.defender.id!!, defenderEloChange)

        val challengerEloChange: Map<String, Int> = mapOf("rating" to challengerChange)
        unitTrainerService.modify(battle.challenger.id!!, challengerEloChange)

        return createdBattle
    }

}
