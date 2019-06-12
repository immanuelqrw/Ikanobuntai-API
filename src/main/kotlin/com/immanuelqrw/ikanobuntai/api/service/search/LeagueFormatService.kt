package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.Format
import com.immanuelqrw.ikanobuntai.api.entity.League
import com.immanuelqrw.ikanobuntai.api.entity.LeagueFormat
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class LeagueFormatService : BaseUniqueService<LeagueFormat>() {

    fun findAllLeagueFormatsByLeague(leagueId: UUID): List<LeagueFormat> {
        return findAll("leagueId:$leagueId")
    }

    fun findAllLeagueFormatsByFormat(format: Format): List<LeagueFormat> {
        return findAll("format:${format.name}")
    }

    fun findAllFormatsByLeague(leagueId: UUID): List<Format> {
        return findAllLeagueFormatsByLeague(leagueId).map { leagueFormat ->
            leagueFormat.format
        }
    }

    fun findAllLeaguesByFormat(format: Format): List<League> {
        return findAllLeagueFormatsByFormat(format).map { leagueFormat ->
            leagueFormat.league
        }
    }
}
