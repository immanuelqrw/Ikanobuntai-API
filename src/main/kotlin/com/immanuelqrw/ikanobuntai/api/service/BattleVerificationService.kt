package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.TITLE_RANK_DIFFERENCE
import com.immanuelqrw.ikanobuntai.api.dto.BattleVerification
import com.immanuelqrw.ikanobuntai.api.entity.Battle
import com.immanuelqrw.ikanobuntai.api.entity.BattleType
import com.immanuelqrw.ikanobuntai.api.entity.BattleVerificationType
import com.immanuelqrw.ikanobuntai.api.entity.Tier
import com.immanuelqrw.ikanobuntai.api.entity.TierTitle
import com.immanuelqrw.ikanobuntai.api.exception.InvalidBattleException
import com.immanuelqrw.ikanobuntai.api.service.search.BattleService
import com.immanuelqrw.ikanobuntai.api.service.search.PrizeGruntService
import com.immanuelqrw.ikanobuntai.api.service.search.TrainerRatingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class BattleVerificationService {

    @Autowired
    private lateinit var battleService: BattleService

    @Autowired
    private lateinit var prizeGruntService: PrizeGruntService

    @Autowired
    private lateinit var trainerRatingService: TrainerRatingService

    fun acquireType(battleVerification: BattleVerification): BattleVerificationType? {
        battleVerification.run {
            if (battleType.hasPrize) {
                validatePrizeBattle(challengerId, leagueId!!, tierTitle!!)
                return BattleVerificationType.PRIZE
            }

            return when (battleType) {
                BattleType.TITLE -> {
                    validateTitleBattle(defenderId, challengerId, tierTitle!!.tier)
                    BattleVerificationType.TITLE
                }
                BattleType.WILD -> BattleVerificationType.WILD
                else -> BattleVerificationType.NON_RANKED
            }
        }
    }

    private fun validatePrizeBattle(challengerId: UUID, leagueId: UUID, tierTitle: TierTitle) {
        val prizeGrunts = prizeGruntService.findAllByLeagueTitle(leagueId, tierTitle.id!!).toSet()
        val allBattles: List<Battle> = battleService.findAllByWinner(challengerId)
        val gruntWins = allBattles.filter { wonBattle ->
            val trainers = setOf(wonBattle.defender.id, wonBattle.challenger.id)

            wonBattle.type.isGrunt && prizeGrunts.any { prizeGrunt ->
                prizeGrunt.trainer.id in trainers
            }
        }.size

        if (gruntWins < tierTitle.title.gruntRequirement) {
            throw InvalidBattleException("Too few grunt wins to challenge for ${tierTitle.prize.name}")
        }
    }

    private fun validateTitleBattle(defenderId: UUID, challengerId: UUID, tier: Tier) {
        val defenderRating = trainerRatingService.findByTrainerTier(defenderId, tier)!!
        val challengerRating = trainerRatingService.findByTrainerTier(challengerId, tier)!!

        val rankDifference = defenderRating.rank.level - challengerRating.rank.level

        if (rankDifference > TITLE_RANK_DIFFERENCE) {
            throw InvalidBattleException("Rank too low to challenger for title")
        }
    }

}
