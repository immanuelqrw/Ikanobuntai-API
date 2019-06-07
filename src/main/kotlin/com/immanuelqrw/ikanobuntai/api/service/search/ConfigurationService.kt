package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.ikanobuntai.api.entity.Configuration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID
import com.immanuelqrw.ikanobuntai.api.service.unit.ConfigurationService as UnitConfigurationService

@Service
class ConfigurationService {

    @Autowired
    private lateinit var configurationService: UnitConfigurationService

    fun findByLeagueFormat(leagueFormatId: UUID): Configuration? {
        return configurationService.findAll("leagueFormatId:$leagueFormatId").firstOrNull()
    }

}
