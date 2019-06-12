package com.immanuelqrw.ikanobuntai.api.service.battle

import com.immanuelqrw.ikanobuntai.api.dto.BattleVerification
import com.immanuelqrw.ikanobuntai.api.dto.PokemonBattle
import com.immanuelqrw.ikanobuntai.api.dto.TeamVerification
import com.immanuelqrw.ikanobuntai.api.entity.Battle
import com.immanuelqrw.ikanobuntai.api.entity.BattleResult
import com.immanuelqrw.ikanobuntai.api.entity.BattleVerificationType
import com.immanuelqrw.ikanobuntai.api.entity.League
import com.immanuelqrw.ikanobuntai.api.entity.Trainer
import com.immanuelqrw.ikanobuntai.api.service.search.BattleService
import com.immanuelqrw.ikanobuntai.api.service.search.LeagueService
import com.immanuelqrw.ikanobuntai.api.service.search.TrainerRatingService
import com.immanuelqrw.ikanobuntai.api.service.search.TrainerService
import com.immanuelqrw.ikanobuntai.api.service.search.TrainerTitleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class PokemonBattleService {

    @Autowired
    private lateinit var battleService: BattleService

    @Autowired
    private lateinit var trainerService: TrainerService

    @Autowired
    private lateinit var eloService: EloService

    @Autowired
    private lateinit var trainerRatingService: TrainerRatingService

    @Autowired
    private lateinit var rankService: RankService

    @Autowired
    private lateinit var trainerTitleService: TrainerTitleService

    @Autowired
    private lateinit var leagueService: LeagueService

    @Autowired
    private lateinit var battlePrizeService: BattlePrizeService

    @Autowired
    private lateinit var battleVerificationService: BattleVerificationService

    @Autowired
    private lateinit var teamVerificationService: TeamVerificationService

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

        val teamVerification = TeamVerification(
            defenderTeam = pokemonBattle.defenderTeam,
            challengerTeam = pokemonBattle.challengerTeam,
            leagueId = league?.id
        )

        val battle = Battle(
            type = pokemonBattle.type,
            defender = defender,
            challenger = challenger,
            winner = winner,
            league = league,
            foughtOn = pokemonBattle.foughtOn
        )

        val battleResult = when (battle.winner) {
            defender -> BattleResult.WIN
            challenger -> BattleResult.LOSS
            else -> BattleResult.DRAW
        }

        // No Elo change during prize/title matches
        val battleVerificationType = battleVerificationService.acquireType(battleVerification)
        teamVerificationService.validateTeams(teamVerification)
        val createdBattle: Battle = battleService.create(battle)

        when(battleVerificationType) {
            BattleVerificationType.TITLE -> {
                pokemonBattle.run {
                    updateTitle(challenger, battleResult, defendingTierTitle?.id!!, foughtOn)
                }
            }
            BattleVerificationType.PRIZE -> {
                updatePrize(defender, challenger, battleResult, league!!)
            }
            else -> {
                // Non League matches don't alter elo
                updateEloRank(league, battleResult, defender.id!!, challenger.id!!)
            }
        }

        // - Consider returning custom output like changed elo, ranking, title of each trainer
        return createdBattle
    }

    private fun hasChallengerWon(battleResult: BattleResult): Boolean {
        // BattleResult is from Defender perspective
        return battleResult == BattleResult.LOSS
    }

    private fun updateTitle(challenger: Trainer, battleResult: BattleResult, defendingTierTitleId: UUID, foughtOn: LocalDateTime) {
        if (hasChallengerWon(battleResult)) {
            // ! Need to refresh Grunts if Title Holder is auto-grunt
            trainerTitleService.transferTitle(challenger, defendingTierTitleId, foughtOn)
        }
    }

    private fun updatePrize(defender: Trainer, challenger: Trainer, battleResult: BattleResult, league: League)  {
        if (hasChallengerWon(battleResult)) {
            battlePrizeService.grantPrize(defender.id!!, challenger, league)
        }
    }

    private fun updateEloRank(league: League?, battleResult: BattleResult, defenderId: UUID, challengerId: UUID) {
        league?.run {
            val defenderRating = trainerRatingService.findByTrainerTier(defenderId, tier)!!
            val challengerRating = trainerRatingService.findByTrainerTier(challengerId, tier)!!

            val (defenderEloChange, challengerEloChange) = eloService.calculateBattle(defenderRating.elo, challengerRating.elo, battleResult, kFactor)

            val defenderChange: Map<String, Any> = mapOf(
                "elo" to defenderEloChange,
                "rank" to rankService.checkRank(defenderId, defenderEloChange, defenderRating.rank, tier)
            )
            trainerRatingService.modify(defenderRating.id!!, defenderChange)

            val challengerChange: Map<String, Any> = mapOf(
                "elo" to challengerEloChange,
                "rank" to rankService.checkRank(challengerId, challengerEloChange, challengerRating.rank, tier)
            )
            trainerRatingService.modify(challengerRating.id!!, challengerChange)
        }

    }

}
