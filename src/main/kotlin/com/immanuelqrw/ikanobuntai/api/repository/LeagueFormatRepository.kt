package com.immanuelqrw.ikanobuntai.api.repository

import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.ikanobuntai.api.entity.Format
import com.immanuelqrw.ikanobuntai.api.entity.LeagueFormat

/**
 * Repository interface for [LeagueFormat]
 */
interface LeagueFormatRepository : BaseUniqueRepository<LeagueFormat> {

    fun findAllByLeagueNameAndRemovedOnIsNull(leagueName: String): List<LeagueFormat>

    fun findAllByFormatAndRemovedOnIsNull(format: Format): List<LeagueFormat>

}
