package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.Format
import com.immanuelqrw.ikanobuntai.api.entity.League
import com.immanuelqrw.ikanobuntai.api.entity.LeagueFormat
import com.immanuelqrw.ikanobuntai.api.repository.LeagueFormatRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LeagueFormatSeekService : BaseUniqueService<LeagueFormat>(LeagueFormat::class.java) {

    @Autowired
    private lateinit var leagueFormatRepository: LeagueFormatRepository

    fun findAllLeagueFormatsByLeague(leagueName: String): List<LeagueFormat> {
        return leagueFormatRepository.findAllByLeagueNameAndRemovedOnIsNull(leagueName)
    }

    fun findAllLeagueFormatsByFormat(format: Format): List<LeagueFormat> {
        return leagueFormatRepository.findAllByFormatAndRemovedOnIsNull(format)
    }

    fun findAllFormatsByLeague(leagueName: String): List<Format> {
        return findAllLeagueFormatsByLeague(leagueName).map { leagueFormat -> leagueFormat.format }
    }

    fun findAllLeaguesByFormat(format: Format): List<League> {
        return findAllLeagueFormatsByFormat(format).map { leagueFormat -> leagueFormat.league }
    }
}
