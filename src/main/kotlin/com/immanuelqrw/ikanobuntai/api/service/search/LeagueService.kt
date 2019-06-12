package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.League
import org.springframework.stereotype.Service

@Service
class LeagueService : BaseUniqueService<League>() {

    fun findByName(name: String): League? {
        return findAll("name:$name").firstOrNull()
    }

}
