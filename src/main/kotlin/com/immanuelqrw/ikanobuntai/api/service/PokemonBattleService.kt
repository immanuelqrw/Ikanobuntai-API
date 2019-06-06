package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.dto.BattleVerification
import com.immanuelqrw.ikanobuntai.api.dto.PokemonBattle
import com.immanuelqrw.ikanobuntai.api.entity.Battle
import com.immanuelqrw.ikanobuntai.api.entity.BattleResult
import com.immanuelqrw.ikanobuntai.api.entity.BattleType
import com.immanuelqrw.ikanobuntai.api.entity.Trainer
import com.immanuelqrw.ikanobuntai.api.service.search.LeagueService
import com.immanuelqrw.ikanobuntai.api.service.search.TrainerPrizeService
import com.immanuelqrw.ikanobuntai.api.service.search.TrainerRatingService
import com.immanuelqrw.ikanobuntai.api.service.search.TrainerService
import com.immanuelqrw.ikanobuntai.api.service.search.TrainerTitleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.BattleService as UnitBattleService
import com.immanuelqrw.ikanobuntai.api.service.unit.TrainerRatingService as UnitTrainerRatingService
import com.immanuelqrw.ikanobuntai.api.service.unit.TrainerService as UnitTrainerService

@Service
class PokemonBattleService {

    @Autowired
    private lateinit var battleService: UnitBattleService

    @Autowired
    private lateinit var trainerService: TrainerService

    @Autowired
    private lateinit var eloService: EloService

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

    @Autowired
    private lateinit var battleVerificationService: BattleVerificationService

    // ! Add limiter on who's challenger is valid for Prizes in a BattleScheduler
    fun create(pokemonBattle: PokemonBattle): Battle {
        val defender = trainerService.findByName(pokemonBattle.defender)!!
        val challenger = trainerService.findByName(pokemonBattle.challenger)!!
        val winner: Trainer? = when(pokemonBattle.winner) {
            pokemonBattle.defender -> defender
            pokemonBattle.challenger -> challenger
            else -> null
        }

        val league = pokemonBattle.league?.let { league ->
            leagueService.findByName(league)
        }

        val battleVerification = BattleVerification(
            defenderId = defender.id!!,
            challengerId = challenger.id!!,
            battleType= pokemonBattle.type,
            leagueId = league?.id,
            tierTitle = pokemonBattle.defendingTierTitle
        )

        // ! Throw error if battle is not valid
        battleVerificationService.isValid(battleVerification)

        val battle = Battle(
            type = pokemonBattle.type,
            defender = defender,
            challenger = challenger,
            winner = winner,
            league = league,
            foughtOn = pokemonBattle.foughtOn
        )

        val createdBattle: Battle = battleService.create(battle)

        // No Elo change during title matches
        if (battle.type == BattleType.TITLE) {
            if (challenger == winner) {
                // ! Need to refresh Grunts if Title Holder is auto-grunt
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

                val (defenderEloChange, challengerEloChange) = eloService.calculateBattle(defenderRating.elo, challengerRating.elo, battleResult, it.kFactor)

                val defenderChange: Map<String, Any> = mapOf(
                    "elo" to defenderEloChange,
                    "rank" to rankService.checkRank(defender.id!!, defenderEloChange, defenderRating.rank)
                )
                unitTrainerRatingService.modify(defenderRating.id!!, defenderChange)

                val challengerChange: Map<String, Any> = mapOf(
                    "elo" to challengerEloChange,
                    "rank" to rankService.checkRank(challenger.id!!, challengerEloChange, challengerRating.rank)
                )
                unitTrainerRatingService.modify(challengerRating.id!!, challengerChange)
            }
        }

        return createdBattle
    }

}
