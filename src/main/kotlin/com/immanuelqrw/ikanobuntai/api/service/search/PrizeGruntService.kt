package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.PrizeGrunt
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PrizeGruntService : BaseUniqueService<PrizeGrunt>() {

    fun findByLeagueTrainer(leagueId: UUID, trainerId: UUID): PrizeGrunt? {
        return findAll("leagueId:$leagueId;trainerId:$trainerId").firstOrNull()
    }

    fun findAllByLeagueTitle(leagueId: UUID, tierTitleId: UUID): List<PrizeGrunt> {
        return findAll("leagueId:$leagueId;tierTitleId:$tierTitleId")
    }

}
