package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.TITLE_RANK_DIFFERENCE
import com.immanuelqrw.ikanobuntai.api.dto.BattleVerification
import com.immanuelqrw.ikanobuntai.api.entity.Battle
import com.immanuelqrw.ikanobuntai.api.entity.BattleType
import com.immanuelqrw.ikanobuntai.api.entity.Tier
import com.immanuelqrw.ikanobuntai.api.entity.TierTitle
import com.immanuelqrw.ikanobuntai.api.service.search.BattleService
import com.immanuelqrw.ikanobuntai.api.service.search.PrizeGruntService
import com.immanuelqrw.ikanobuntai.api.service.search.TrainerRatingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID
import com.immanuelqrw.ikanobuntai.api.service.unit.BattleService as UnitBattleService

@Service
class BattleVerificationService {

    @Autowired
    private lateinit var battleService: BattleService

    @Autowired
    private lateinit var prizeGruntService: PrizeGruntService

    @Autowired
    private lateinit var trainerRatingService: TrainerRatingService

    fun isValid(battleVerification: BattleVerification): Boolean {
        battleVerification.run {
            if (battleType.hasPrize) {
                return isPrizeBattleValid(challengerId, leagueId!!, tierTitle!!)
            } else if (battleType == BattleType.TITLE) {
                return isTitleBattleValid(defenderId, challengerId, tierTitle!!.tier)
            }

            return when (battleType) {
                BattleType.NON_RANKED, BattleType.WILD -> true
                else -> false // ! Get stuff
            }
        }
    }

    private fun isPrizeBattleValid(trainerId: UUID, leagueId: UUID, tierTitle: TierTitle): Boolean {
        val prizeGrunts = prizeGruntService.findAllByLeagueTitle(leagueId, tierTitle.id!!).toSet()
        val allBattles: List<Battle> = battleService.findAllByWinner(trainerId)
        val gruntWins = allBattles.filter { wonBattle ->
            val trainers = setOf(wonBattle.defender.id, wonBattle.challenger.id)

            wonBattle.type.isGrunt && prizeGrunts.any { prizeGrunt ->
                prizeGrunt.trainer.id in trainers
            }
        }.size

        return gruntWins >= tierTitle.title.gruntRequirement
    }

    private fun isTitleBattleValid(defenderId: UUID, challengerId: UUID, tier: Tier): Boolean {
        val defenderRating = trainerRatingService.findByTrainerTier(defenderId, tier)!!
        val challengerRating = trainerRatingService.findByTrainerTier(challengerId, tier)!!

        val rankDifference = defenderRating.rank.level - challengerRating.rank.level

        return rankDifference <= TITLE_RANK_DIFFERENCE
    }

}
