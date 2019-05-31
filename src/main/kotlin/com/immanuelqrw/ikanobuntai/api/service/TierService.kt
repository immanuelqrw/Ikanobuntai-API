package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.entity.Tier
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.TierService as UnitTierService

@Service
class TierService {

    @Autowired
    private lateinit var tierService: UnitTierService

    fun findByName(name: String): Tier? {
        val page = PageRequest.of(1, 1)

        return tierService.findAll(page, "name:$name").content.firstOrNull()
    }

}
