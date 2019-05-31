package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.entity.Ability
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.AbilityService as UnitAbilityService

@Service
class AbilityService {

    @Autowired
    private lateinit var abilityService: UnitAbilityService

    fun findByName(name: String): Ability? {
        val page = PageRequest.of(1, 1)

        return abilityService.findAll(page, "name:$name").content.firstOrNull()
    }

}
