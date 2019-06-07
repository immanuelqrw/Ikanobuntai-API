package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.ikanobuntai.api.entity.League
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.LeagueService as UnitLeagueService

@Service
class LeagueService {

    @Autowired
    private lateinit var leagueService: UnitLeagueService

    fun findByName(name: String): League? {
        return leagueService.findAll("name:$name").firstOrNull()
    }

}
