package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.ikanobuntai.api.entity.PrizeGrunt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID
import com.immanuelqrw.ikanobuntai.api.service.unit.PrizeGruntService as UnitPrizeGruntService

@Service
class PrizeGruntService {

    @Autowired
    private lateinit var prizeGruntService: UnitPrizeGruntService

    fun findByLeagueTrainer(leagueId: UUID, trainerId: UUID): PrizeGrunt? {
        return prizeGruntService.findAll("leagueId:$leagueId;trainerId:$trainerId").firstOrNull()
    }

    fun findAllByLeagueTitle(leagueId: UUID, tierTitleId: UUID): List<PrizeGrunt> {
        return prizeGruntService.findAll("leagueId:$leagueId;tierTitleId:$tierTitleId")
    }

}
