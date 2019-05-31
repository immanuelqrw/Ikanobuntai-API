package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.entity.League
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.LeagueService as UnitLeagueService

@Service
class LeagueService {

    @Autowired
    private lateinit var leagueService: UnitLeagueService

    fun findByName(name: String): League? {
        val page = PageRequest.of(1, 1)

        return leagueService.findAll(page, "name:$name").content.firstOrNull()
    }

}
