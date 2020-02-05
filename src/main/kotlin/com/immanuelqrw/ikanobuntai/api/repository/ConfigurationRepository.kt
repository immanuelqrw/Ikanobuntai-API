package com.immanuelqrw.ikanobuntai.api.repository

import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.ikanobuntai.api.entity.Configuration
import com.immanuelqrw.ikanobuntai.api.entity.Format

/**
 * Repository interface for [Configuration]
 */
interface ConfigurationRepository : BaseUniqueRepository<Configuration> {

    fun findByLeagueFormatLeagueNameAndLeagueFormatFormatAndRemovedOnIsNull(leagueName: String, format: Format): Configuration?

}
