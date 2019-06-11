package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.ikanobuntai.api.entity.Format
import com.immanuelqrw.ikanobuntai.api.entity.League
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID
import com.immanuelqrw.ikanobuntai.api.service.unit.LeagueFormatService as UnitLeagueFormatService

@Service
class LeagueFormatService {

    @Autowired
    private lateinit var leagueFormatService: UnitLeagueFormatService

    fun findAllFormatsByLeague(leagueId: UUID): List<Format> {
        return leagueFormatService.findAll("leagueId:$leagueId").map { leagueFormat ->
            leagueFormat.format
        }
    }

    fun findAllLeaguesByFormat(format: Format): List<League> {
        return leagueFormatService.findAll("format:${format.name}").map { leagueFormat ->
            leagueFormat.league
        }
    }

}
