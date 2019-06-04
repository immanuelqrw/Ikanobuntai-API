package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.entity.Prize
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.PrizeService as UnitPrizeService

@Service
class PrizeService {

    @Autowired
    private lateinit var prizeService: UnitPrizeService

    fun findByName(name: String): Prize? {
        val page = PageRequest.of(1, 1)

        return prizeService.findAll(page, "name:$name").content.firstOrNull()
    }

    // - Add image insert

}
