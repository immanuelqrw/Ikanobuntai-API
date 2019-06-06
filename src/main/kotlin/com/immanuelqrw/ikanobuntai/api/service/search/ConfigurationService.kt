package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.ikanobuntai.api.UNIQUE_PAGE_REQUEST
import com.immanuelqrw.ikanobuntai.api.entity.Move
import com.immanuelqrw.ikanobuntai.api.entity.Tier
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.MoveService as UnitMoveService

@Service
class ConfigurationService {

    @Autowired
    private lateinit var moveService: UnitMoveService

    fun findByNameTier(name: String, tier: Tier): Move? {
        return moveService.findAll(UNIQUE_PAGE_REQUEST, "name:$name;tier:${tier.name}").content.firstOrNull()
    }

}
