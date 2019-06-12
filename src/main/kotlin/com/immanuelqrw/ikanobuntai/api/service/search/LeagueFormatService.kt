package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.Format
import com.immanuelqrw.ikanobuntai.api.entity.League
import com.immanuelqrw.ikanobuntai.api.entity.LeagueFormat
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class LeagueFormatService : BaseUniqueService<LeagueFormat>() {

    fun findAllFormatsByLeague(leagueId: UUID): List<Format> {
        return findAll("leagueId:$leagueId").map { leagueFormat ->
            leagueFormat.format
        }
    }

    fun findAllLeaguesByFormat(format: Format): List<League> {
        return findAll("format:${format.name}").map { leagueFormat ->
            leagueFormat.league
        }
    }
}
