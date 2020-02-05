package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.Configuration
import com.immanuelqrw.ikanobuntai.api.entity.Format
import com.immanuelqrw.ikanobuntai.api.repository.ConfigurationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class ConfigurationSeekService : BaseUniqueService<Configuration>(Configuration::class.java) {

    @Autowired
    private lateinit var configurationRepository: ConfigurationRepository

    fun findByLeagueFormat(leagueName: String, format: Format): Configuration {
        return configurationRepository.findByLeagueFormatLeagueNameAndLeagueFormatFormatAndRemovedOnIsNull(leagueName, format)
            ?: throw EntityNotFoundException()
    }

}
