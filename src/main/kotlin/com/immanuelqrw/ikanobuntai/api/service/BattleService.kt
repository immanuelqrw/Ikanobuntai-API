package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.dto.PokemonBattle
import com.immanuelqrw.ikanobuntai.api.entity.Battle
import com.immanuelqrw.ikanobuntai.api.entity.BattleResult
import com.immanuelqrw.ikanobuntai.api.entity.BattleType
import com.immanuelqrw.ikanobuntai.api.entity.Trainer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.BattleService as UnitBattleService
import com.immanuelqrw.ikanobuntai.api.service.unit.TrainerRatingService as UnitTrainerRatingService
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
    private lateinit var trainerRatingService: TrainerRatingService

    @Autowired
    private lateinit var unitTrainerRatingService: UnitTrainerRatingService

    @Autowired
    private lateinit var rankService: RankService

    @Autowired
    private lateinit var trainerTitleService: TrainerTitleService

    @Autowired
    private lateinit var leagueService: LeagueService

    @Autowired
    private lateinit var trainerPrizeService: TrainerPrizeService

    // ! Add limiter on who's challenger is valid for Prizes in a BattleScheduler
    fun create(pokemonBattle: PokemonBattle): Battle {
        val defender = trainerService.findByName(pokemonBattle.defender)
        val challenger = trainerService.findByName(pokemonBattle.challenger)
        val winner: Trainer? = when(pokemonBattle.winner) {
            pokemonBattle.defender -> defender
            pokemonBattle.challenger -> challenger
            else -> null
        }

        val league = pokemonBattle.league?.let { league ->
            leagueService.findByName(league)
        }

        val battle = Battle(
            type = pokemonBattle.type,
            defender = defender!!,
            challenger = challenger!!,
            winner = winner,
            league = league,
            foughtOn = pokemonBattle.foughtOn
        )

        val createdBattle: Battle = battleService.create(battle)

        // No Elo change during title matches
        if (battle.type == BattleType.TITLE) {
            if (challenger == winner) {
                trainerTitleService.transferTitle(challenger, pokemonBattle.defendingTierTitle!!.id!!, pokemonBattle.foughtOn)
            }
        } else if (battle.type.hasPrize) {
            if (challenger == winner) {
                trainerPrizeService.grantPrize(defender.id!!, challenger, league!!)
            }
        } else {
            // Non League matches don't alter elo
            league?.let {
                val battleResult = when(battle.winner) {
                    defender -> BattleResult.WIN
                    challenger -> BattleResult.LOSS
                    else -> BattleResult.DRAW
                }

                val defenderRating = trainerRatingService.findByTrainerTier(defender.id!!, it.tier)!!
                val challengerRating = trainerRatingService.findByTrainerTier(challenger.id!!, it.tier)!!

                val (defenderEloChange, challengerEloChange) = eloCalculationService.calculateBattle(defenderRating, challengerRating, battleResult, it)

                val defenderRatingChange: Map<String, Int> = mapOf("elo" to defenderEloChange)
                unitTrainerRatingService.modify(defenderRating.id!!, defenderRatingChange)
                val defenderRankChange: Map<String, Any> = mapOf("rank" to rankService.checkRank(defender.id!!, defenderEloChange, defender.rank))
                unitTrainerService.modify(battle.defender.id!!, defenderRankChange)

                val challengerRatingChange: Map<String, Int> = mapOf("elo" to challengerEloChange)
                unitTrainerRatingService.modify(challengerRating.id!!, challengerRatingChange)
                val challengerRankChange: Map<String, Any> = mapOf("rank" to rankService.checkRank(challenger.id!!, defenderEloChange, challenger.rank))
                unitTrainerService.modify(battle.challenger.id!!, challengerRankChange)
            }
        }

        return createdBattle
    }

}
