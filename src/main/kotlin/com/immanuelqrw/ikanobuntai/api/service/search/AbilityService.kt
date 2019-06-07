package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.ikanobuntai.api.entity.Ability
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.AbilityService as UnitAbilityService

@Service
class AbilityService {

    @Autowired
    private lateinit var abilityService: UnitAbilityService

    fun findByName(name: String): Ability? {
        return abilityService.findAll("name:$name").firstOrNull()
    }

}
