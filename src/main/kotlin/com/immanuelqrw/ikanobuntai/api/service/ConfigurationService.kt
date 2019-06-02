package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.entity.Move
import com.immanuelqrw.ikanobuntai.api.entity.Tier
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.MoveService as UnitMoveService

@Service
class ConfigurationService {

    @Autowired
    private lateinit var moveService: UnitMoveService

    fun findByNameTier(name: String, tier: Tier): Move? {
        val page = PageRequest.of(1, 1)

        return moveService.findAll(page, "name:$name;tier:${tier.name}").content.firstOrNull()
    }

}
