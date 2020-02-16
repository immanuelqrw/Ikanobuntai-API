package com.immanuelqrw.ikanobuntai.api.repository

import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.ikanobuntai.api.entity.Configuration
import com.immanuelqrw.ikanobuntai.api.entity.Format
import org.springframework.stereotype.Repository

/**
 * Repository interface for [Configuration]
 */
@Repository
interface ConfigurationRepository : BaseUniqueRepository<Configuration> {

    fun findByLeagueFormatLeagueNameAndLeagueFormatFormatAndRemovedOnIsNull(leagueName: String, format: Format): Configuration?

}
