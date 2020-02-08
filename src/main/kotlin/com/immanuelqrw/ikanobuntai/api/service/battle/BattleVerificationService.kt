package com.immanuelqrw.ikanobuntai.api.service.battle

import com.immanuelqrw.ikanobuntai.api.TITLE_RANK_DIFFERENCE
import com.immanuelqrw.ikanobuntai.api.dto.BattleVerification
import com.immanuelqrw.ikanobuntai.api.entity.*
import com.immanuelqrw.ikanobuntai.api.exception.InvalidBattleException
import com.immanuelqrw.ikanobuntai.api.service.seek.BattleSeekService
import com.immanuelqrw.ikanobuntai.api.service.seek.PrizeGruntSeekService
import com.immanuelqrw.ikanobuntai.api.service.seek.TrainerRatingSeekService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BattleVerificationService {

    @Autowired
    private lateinit var battleSeekService: BattleSeekService

    @Autowired
    private lateinit var prizeGruntSeekService: PrizeGruntSeekService

    @Autowired
    private lateinit var trainerRatingSeekService: TrainerRatingSeekService

    fun acquireType(battleVerification: BattleVerification): BattleVerificationType? {
        battleVerification.run {
            if (battleType.hasPrize) {
                validatePrizeBattle(challengerName, leagueName, tierTitle!!)
                return BattleVerificationType.PRIZE
            }

            return when (battleType) {
                BattleType.TITLE -> {
                    validateTitleBattle(defenderName, challengerName, tierTitle!!.tier)
                    BattleVerificationType.TITLE
                }
                BattleType.WILD -> BattleVerificationType.WILD
                else -> BattleVerificationType.NON_RANKED
            }
        }
    }

    private fun validatePrizeBattle(challengerName: String, leagueName: String, tierTitle: TierTitle) {
        val prizeGrunts: Set<PrizeGrunt> = prizeGruntSeekService.findAllByLeagueAndTierAndTitle(leagueName, tierTitle.tier, tierTitle.title).toSet()
        val allBattles: List<Battle> = battleSeekService.findAllByWinner(challengerName)
        val gruntWins: Int = allBattles.filter { wonBattle ->
            val trainers = setOf(wonBattle.defender.id, wonBattle.challenger.id)

            wonBattle.type.isGrunt && prizeGrunts.any { prizeGrunt ->
                prizeGrunt.trainer.id in trainers
            }
        }.size

        if (gruntWins < tierTitle.title.gruntRequirement) {
            throw InvalidBattleException("Too few grunt wins to challenge for ${tierTitle.prize.name}")
        }
    }

    private fun validateTitleBattle(defenderName: String, challengerName: String, tier: Tier) {
        val defenderRating: TrainerRating = trainerRatingSeekService.findByTrainerAndTier(defenderName, tier)
        val challengerRating: TrainerRating = trainerRatingSeekService.findByTrainerAndTier(challengerName, tier)

        val rankDifference: Int = defenderRating.rank.level - challengerRating.rank.level

        if (rankDifference > TITLE_RANK_DIFFERENCE) {
            throw InvalidBattleException("Rank too low to challenger for title")
        }
    }

}
