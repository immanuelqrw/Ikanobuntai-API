package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.entity.Rank
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.RankService as UnitRankService

@Service
class RankService {

    @Autowired
    private lateinit var rankService: UnitRankService

    fun findByName(name: String): Rank? {
        val page = PageRequest.of(1, 1)

        return rankService.findAll(page, "name:$name").content.firstOrNull()
    }

}
