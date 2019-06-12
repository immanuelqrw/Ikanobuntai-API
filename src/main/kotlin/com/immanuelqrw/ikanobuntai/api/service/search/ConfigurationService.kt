package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.Configuration
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ConfigurationService : BaseUniqueService<Configuration>() {

    fun findByLeagueFormat(leagueFormatId: UUID): Configuration? {
        return findAll("leagueFormatId:$leagueFormatId").firstOrNull()
    }

}
