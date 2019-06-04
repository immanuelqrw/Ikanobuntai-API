package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.UNIQUE_PAGE_REQUEST
import com.immanuelqrw.ikanobuntai.api.entity.Ability
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.AbilityService as UnitAbilityService

@Service
class AbilityService {

    @Autowired
    private lateinit var abilityService: UnitAbilityService

    fun findByName(name: String): Ability? {
        return abilityService.findAll(UNIQUE_PAGE_REQUEST, "name:$name").content.firstOrNull()
    }

}
