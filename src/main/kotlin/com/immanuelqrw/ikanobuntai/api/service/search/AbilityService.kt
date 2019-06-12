package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.Ability
import org.springframework.stereotype.Service

@Service
class AbilityService : BaseUniqueService<Ability>() {

    fun findByName(name: String): Ability? {
        return findAll("name:$name").firstOrNull()
    }

}
