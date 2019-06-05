package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.UNIQUE_PAGE_REQUEST
import com.immanuelqrw.ikanobuntai.api.entity.League
import com.immanuelqrw.ikanobuntai.api.entity.PrizeGrunt
import com.immanuelqrw.ikanobuntai.api.entity.TierTitle
import com.immanuelqrw.ikanobuntai.api.entity.Trainer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.PrizeGruntService as UnitPrizeGruntService

@Service
class PrizeGruntService {

    @Autowired
    private lateinit var prizeGruntService: UnitPrizeGruntService

    fun findByLeagueTrainer(league: League, trainer: Trainer): PrizeGrunt? {
        return prizeGruntService.findAll(UNIQUE_PAGE_REQUEST, "league:${league.id};trainer:${trainer.id}").content.firstOrNull()
    }

    fun findAllByLeagueTitle(league: League, tierTitle: TierTitle): List<PrizeGrunt> {
        // - Move to configurable value
        val page = PageRequest.of(1, 100)
        return prizeGruntService.findAll(page, "league:${league.id};tierTitle:${tierTitle.id}").content
    }

}
