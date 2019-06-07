package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.entity.Rank
import com.immanuelqrw.ikanobuntai.api.entity.Rank.*
import com.immanuelqrw.ikanobuntai.api.entity.Tier
import com.immanuelqrw.ikanobuntai.api.entity.Title.*
import com.immanuelqrw.ikanobuntai.api.service.search.TrainerTitleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class RankService {

    @Autowired
    private lateinit var trainerTitleService: TrainerTitleService

    fun checkRank(trainerId: UUID, elo: Int, currentRank: Rank, tier: Tier): Rank {
        val titleRank = checkTitle(trainerId, tier)
        val eloRank = checkElo(elo)

        val ranks = listOf(titleRank, eloRank, currentRank)

        return ranks.maxBy { rank: Rank ->
            rank.level
        }!!
    }

    private fun checkElo(elo: Int): Rank {
        return when {
            elo >= MASTER.minElo -> MASTER
            elo >= WORLD_RANKER.minElo -> WORLD_RANKER
            elo >= WORLD_CHALLENGER.minElo -> WORLD_CHALLENGER
            elo >= ASCENDANT.minElo -> ASCENDANT
            elo >= LEAGUE_FINALIST.minElo -> LEAGUE_FINALIST
            elo >= LEAGUE_CHALLENGER.minElo -> LEAGUE_CHALLENGER
            elo >= ACE_TRAINER.minElo -> ACE_TRAINER
            elo >= TRAINER.minElo -> TRAINER
            else -> JUNIOR_TRAINER
        }
    }

    private fun checkTitle(trainerId: UUID, tier: Tier): Rank {
        val trainerTitle = trainerTitleService.findByTrainerIdTier(trainerId, tier)

        return when(trainerTitle?.tierTitle?.title) {
            WORLD_NOBLE,
            WORLD_CHAMPION -> MASTER

            WORLD_RANKED_10,
            WORLD_RANKED_09,
            WORLD_RANKED_08,
            WORLD_RANKED_07,
            WORLD_RANKED_06,
            WORLD_RANKED_05,
            WORLD_RANKED_04,
            WORLD_RANKED_03,
            WORLD_RANKED_02,
            WORLD_RANKED_01 -> WORLD_RANKER

            LEAGUE_BARON,
            LEAGUE_CHAMPION,
            ARENA_TYCOON,
            DOME_ACE,
            FACTORY_HEAD,
            PALACE_MAVEN,
            PIKE_QUEEN,
            PYRAMID_KING,
            SALON_MAIDEN,
            TOWER_TYCOON,
            ARCADE_STAR,
            CASTLE_VALET,
            HALL_MATRON -> ASCENDANT

            LEAGUE_RANKED_05,
            ELITE_FOUR -> LEAGUE_FINALIST

            GYM_LEADER -> LEAGUE_CHALLENGER

            else -> JUNIOR_TRAINER
        }
    }
}
