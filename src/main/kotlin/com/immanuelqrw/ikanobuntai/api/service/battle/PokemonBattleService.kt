package com.immanuelqrw.ikanobuntai.api.service.battle

import com.immanuelqrw.ikanobuntai.api.dto.BattleVerification
import com.immanuelqrw.ikanobuntai.api.dto.PokemonBattle
import com.immanuelqrw.ikanobuntai.api.dto.TeamVerification
import com.immanuelqrw.ikanobuntai.api.entity.*
import com.immanuelqrw.ikanobuntai.api.service.seek.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PokemonBattleService {

    @Autowired
    private lateinit var battleSeekService: BattleSeekService

    @Autowired
    private lateinit var trainerSeekService: TrainerSeekService

    @Autowired
    private lateinit var leagueTrainerSeekService: LeagueTrainerSeekService

    @Autowired
    private lateinit var eloService: EloService

    @Autowired
    private lateinit var trainerRatingSeekService: TrainerRatingSeekService

    @Autowired
    private lateinit var rankService: RankService

    @Autowired
    private lateinit var trainerTitleSeekService: TrainerTitleSeekService

    @Autowired
    private lateinit var leagueSeekService: LeagueSeekService

    @Autowired
    private lateinit var battlePrizeService: BattlePrizeService

    @Autowired
    private lateinit var battleVerificationService: BattleVerificationService

    @Autowired
    private lateinit var teamVerificationService: TeamVerificationService

    @Autowired
    private lateinit var scheduledBattleSeekService: ScheduledBattleSeekService

    fun create(pokemonBattle: PokemonBattle): Battle {
        val league: League = leagueSeekService.findByName(pokemonBattle.league)

        val defender: Trainer = trainerSeekService.findByName(pokemonBattle.defender)

        val challenger: Trainer = trainerSeekService.findByName(pokemonBattle.challenger)

        val winner: Trainer? = when(pokemonBattle.winner) {
            pokemonBattle.defender -> defender
            pokemonBattle.challenger -> challenger
            else -> null
        }

        val battleVerification = BattleVerification(
            defenderName = defender.name,
            challengerName = challenger.name,
            battleType = pokemonBattle.type,
            leagueName = league.name,
            tierTitle = pokemonBattle.defendingTierTitle
        )

        val teamVerification = TeamVerification(
            defenderTeam = pokemonBattle.defenderTeam,
            challengerTeam = pokemonBattle.challengerTeam,
            leagueName = league.name
        )

        val battle = Battle(
            type = pokemonBattle.type,
            defender = defender,
            challenger = challenger,
            winner = winner,
            league = league,
            foughtOn = pokemonBattle.foughtOn
        )

        val battleResult: BattleResult = when (battle.winner) {
            defender -> BattleResult.WIN
            challenger -> BattleResult.LOSS
            else -> BattleResult.DRAW
        }

        // No Elo change during prize/title matches
        val battleVerificationType = battleVerificationService.acquireType(battleVerification)
        teamVerificationService.validateTeams(teamVerification)
        val createdBattle: Battle = battleSeekService.create(battle)

        when(battleVerificationType) {
            BattleVerificationType.TITLE -> {
                pokemonBattle.run {
                    defendingTierTitle?.let {
                        updateTitle(challenger, battleResult, defendingTierTitle.tier, defendingTierTitle.title, foughtOn)
                    }
                }
            }
            BattleVerificationType.PRIZE -> {
                updatePrize(defender, challenger, battleResult, league)
            }
            else -> {
                // Non League matches don't alter elo
                updateEloRank(league, battleResult, defender.name, challenger.name)
            }
        }

        // - Consider returning custom output like changed elo, ranking, title of each trainer
        // ! Consider if validate that actual battle has same parameters as scheduled
        pokemonBattle.scheduledBattleId?.run {
            val scheduledBattle = scheduledBattleSeekService.find(this).copy(hasConcluded = true)
            scheduledBattleSeekService.replace(this, scheduledBattle)
        }

        return createdBattle
    }

    private fun hasChallengerWon(battleResult: BattleResult): Boolean {
        // BattleResult is from Defender perspective
        return battleResult == BattleResult.LOSS
    }

    private fun updateTitle(challenger: Trainer, battleResult: BattleResult, defendingTier: Tier, defendingTitle: Title, foughtOn: LocalDateTime) {
        if (hasChallengerWon(battleResult)) {
            // ! Need to refresh Grunts if Title Holder is auto-grunt
            trainerTitleSeekService.transferTitle(challenger, defendingTier, defendingTitle, foughtOn)
        }
    }

    private fun updatePrize(defender: Trainer, challenger: Trainer, battleResult: BattleResult, league: League)  {
        if (hasChallengerWon(battleResult)) {
            battlePrizeService.grantPrize(defender.name, challenger, league)
        }
    }

    private fun updateEloRank(league: League, battleResult: BattleResult, defenderName: String, challengerName: String) {
        league.run {
            val defenderRating: TrainerRating = trainerRatingSeekService.findByTrainerAndTier(defenderName, tier)
            val challengerRating: TrainerRating = trainerRatingSeekService.findByTrainerAndTier(challengerName, tier)

            val (defenderEloChange, challengerEloChange) = eloService.calculateBattle(defenderRating.elo, challengerRating.elo, battleResult, kFactor)

            val defenderChange: Map<String, Any> = mapOf(
                "elo" to defenderEloChange,
                "rank" to rankService.checkRank(defenderName, defenderEloChange, defenderRating.rank, tier)
            )
            trainerRatingSeekService.modify(defenderRating.id, defenderChange)

            val challengerChange: Map<String, Any> = mapOf(
                "elo" to challengerEloChange,
                "rank" to rankService.checkRank(challengerName, challengerEloChange, challengerRating.rank, tier)
            )
            trainerRatingSeekService.modify(challengerRating.id, challengerChange)
        }

    }

}
