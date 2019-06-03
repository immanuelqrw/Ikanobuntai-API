package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.dto.PokemonBattle
import com.immanuelqrw.ikanobuntai.api.entity.Battle
import com.immanuelqrw.ikanobuntai.api.entity.BattleType
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
    private lateinit var eloCalculationService: EloCalculationService

    @Autowired
    private lateinit var rankService: RankService

    @Autowired
    private lateinit var trainerTitleService: TrainerTitleService

    fun create(pokemonBattle: PokemonBattle): Battle {
        val defender = trainerService.findByName(pokemonBattle.defender)
        val challenger = trainerService.findByName(pokemonBattle.challenger)
        val winner: Trainer? = when(pokemonBattle.winner) {
            pokemonBattle.defender -> defender
            pokemonBattle.challenger -> challenger
            else -> null
        }
        val rank: Rank = pokemonBattle.rank

        val battle = Battle(
            type = pokemonBattle.type,
            defender = defender!!,
            challenger = challenger!!,
            winner = winner,
            rank = rank,
            value = pokemonBattle.value,
            foughtOn = pokemonBattle.foughtOn
        )

        val createdBattle: Battle = battleService.create(battle)

        // No Elo change during title matches
        if (battle.type == BattleType.TITLE) {
            if (challenger == winner) {
                trainerTitleService.transferTitle(defender, challenger, pokemonBattle.defendingTierTitle!!, pokemonBattle.foughtOn)
            }
        } else {
            val (defenderEloChange, challengerEloChange) = eloCalculationService.calculateBattle(battle)

            val defenderChange: Map<String, Any> = mapOf(
                "rating" to defenderEloChange,
                "rank" to rankService.checkRank(defender.id!!, defenderEloChange, defender.rank)
            )
            unitTrainerService.modify(battle.defender.id!!, defenderChange)

            val challengerChange: Map<String, Any> = mapOf(
                "rating" to challengerEloChange,
                "rank" to rankService.checkRank(challenger.id!!, defenderEloChange, challenger.rank)
            )
            unitTrainerService.modify(battle.challenger.id!!, challengerChange)
        }

        return createdBattle
    }

}
