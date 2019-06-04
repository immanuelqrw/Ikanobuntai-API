package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.UNIQUE_PAGE_REQUEST
import com.immanuelqrw.ikanobuntai.api.entity.League
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.LeagueService as UnitLeagueService

@Service
class LeagueService {

    @Autowired
    private lateinit var leagueService: UnitLeagueService

    fun findByName(name: String): League? {
        return leagueService.findAll(UNIQUE_PAGE_REQUEST, "name:$name").content.firstOrNull()
    }

}
