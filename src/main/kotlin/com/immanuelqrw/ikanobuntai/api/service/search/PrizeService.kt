package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.ikanobuntai.api.UNIQUE_PAGE_REQUEST
import com.immanuelqrw.ikanobuntai.api.entity.Prize
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.PrizeService as UnitPrizeService

@Service
class PrizeService {

    @Autowired
    private lateinit var prizeService: UnitPrizeService

    fun findByName(name: String): Prize? {
        return prizeService.findAll("name:$name").firstOrNull()
    }

    // - Add image insert

}
